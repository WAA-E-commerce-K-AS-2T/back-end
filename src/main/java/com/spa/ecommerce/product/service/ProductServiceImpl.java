package com.spa.ecommerce.product.service;

import com.spa.ecommerce.category.Category;
import com.spa.ecommerce.category.CategoryRepo;
import com.spa.ecommerce.common.ProductStatusEnum;
import com.spa.ecommerce.exception.ProductException;
import com.spa.ecommerce.product.dto.*;
import com.spa.ecommerce.product.entity.Product;
import com.spa.ecommerce.product.repository.CustomProductRepository;
import com.spa.ecommerce.product.repository.ProductRepository;
import com.spa.ecommerce.productPhoto.entity.ProductPhoto;
import com.spa.ecommerce.productPhoto.repository.ProductPhotoRepository;
import com.spa.ecommerce.productPhoto.service.CloudinaryServiceImpl;
import com.spa.ecommerce.review.Review;
import com.spa.ecommerce.review.ReviewDTO;
import com.spa.ecommerce.review.ReviewDTOMapper;
import com.spa.ecommerce.user.User;
import com.spa.ecommerce.user.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.Principal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final UserRepository userRepository;
    private final ProductRepository productRepository;
    private final ProductDTOMapper productDTOMapper;
    private final CloudinaryServiceImpl cloudinaryService;
    private final ProductPhotoRepository productPhotoRepository;
    private final CustomProductRepository customProductRepository;
    private final CategoryRepo categoryRepo;
    private final ReviewDTOMapper reviewDTOMapper;
    private final ProductResponseDTOMapper productResponseDTOMapper;

    @Override
    public ProductResponseDto saveProduct(Principal principal, ProductDTO productDTO, MultipartFile[] photos) {
        String email = principal.getName();
        Optional<User> OptUser = userRepository.findByEmail(email);
        if(OptUser.isPresent()){
            User user = OptUser.get();
            Product product = new Product();
            List<Category> productCategories = new ArrayList<Category>();
            List<Long> catIds = productDTO.getCategoryIds();
            for(Long catId : catIds){
                Optional<Category> cat = categoryRepo.findById(catId);
                if(cat.isPresent()){
                    Category category = cat.get();
                    productCategories.add(category);
                }
            }
            BeanUtils.copyProperties(productDTO, product);
            product.setStatus(ProductStatusEnum.IN_REVIEW);
            product.setTimesBought(0);
            product.setSeller(user);
            product.setCategories(productCategories);
            product.setPostedDate(LocalDate.now());

            for(MultipartFile photo: photos){
                try {
                    Map clodinaryResult = cloudinaryService.upload(photo);
                    ProductPhoto productPhoto = new ProductPhoto();
                    productPhoto.setName((String) clodinaryResult.get("original_filename"));
                    productPhoto.setImageUrl((String) clodinaryResult.get("url"));
                    productPhoto.setImageId((String) clodinaryResult.get("public_id"));
                    productPhoto.setProduct(product);
                    product.addProductPhoto(productPhoto);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            Product p = productRepository.save(product);
            return productResponseDTOMapper.toDto(p);
        }else{
            throw new ProductException("Token not found");
        }
    }

    @Override
    public Page<ProductResponseDto> getAllProducts(Pageable pageable) {
        return productRepository.findAllByStatusAndInStockGreaterThan(ProductStatusEnum.APPROVED,0,pageable).map(productResponseDTOMapper::toDto);
    }

    @Override
    public ProductResponseDto deleteById(Long id) {
        Optional<Product> product = productRepository.findById(id);
        if (product.isPresent()) {
            Product product1 = product.get();
            if (product1.getTimesBought() == 0) {
                productRepository.deleteById(id);
                return productResponseDTOMapper.toDto(product1);
            } else {
                throw new ProductException("Product cannot be deleted");
            }
        }else{
            throw  new ProductException("product not found");
        }
    }

    @Override
    @Transactional
    public ProductResponseDto updateProduct(Long id, ProductDTO productDTO, MultipartFile[] photos) {
        Optional<Product> productOptional = productRepository.findById(id);
        if(productOptional.isPresent()){
           Product existingProduct = productOptional.get();

           //delete existing file on cloudinary
            List<ProductPhoto> productPhotos = existingProduct.getProductPhotos();
            for(ProductPhoto photo: productPhotos){
                try{
                    System.out.println(photo.getName() + " " + photo.getImageId());
                    cloudinaryService.delete(photo.getImageId());
                } catch (Exception e){
                    System.out.println(photo.getName() + " " + photo.getImageId());
                    throw new RuntimeException(e);
                }
                productPhotoRepository.delete(photo);
            }
            existingProduct.getProductPhotos().clear();

            //update
            for(MultipartFile photo: photos){
                try {
                    Map clodinaryResult = cloudinaryService.upload(photo);
                    ProductPhoto productPhoto = new ProductPhoto();
                    productPhoto.setName((String) clodinaryResult.get("original_filename"));
                    productPhoto.setImageUrl((String) clodinaryResult.get("url"));
                    productPhoto.setImageId((String) clodinaryResult.get("public_id"));
                    productPhoto.setProduct(existingProduct);
                    existingProduct.setTimesBought(existingProduct.getTimesBought());
                    existingProduct.addProductPhoto(productPhoto);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }

            List<Category> cats = existingProduct.getCategories();
            for(Long catId : productDTO.getCategoryIds()){
                Optional<Category> cat = categoryRepo.findById(catId);
                if(cat.isPresent()){
                    Category category = cat.get();
                    cats.add(category);
                }
            }


            ProductStatusEnum status = existingProduct.getStatus();
            List<ProductPhoto> productPhoto = existingProduct.getProductPhotos();
            existingProduct.setName(productDTO.getName());
            existingProduct.setDescription(productDTO.getDescription());
            existingProduct.setPrice(productDTO.getPrice());
            existingProduct.setBrand(productDTO.getBrand());
            existingProduct.setColor(productDTO.getColor());
            existingProduct.setMaterial(productDTO.getMaterial());
            existingProduct.setProductSize(productDTO.getProductSize());
            existingProduct.setInStock(productDTO.getInStock());
            existingProduct.setStatus(status);
            existingProduct.setProductPhotos(productPhoto);
           productRepository.save(existingProduct);
           return productResponseDTOMapper.toDto(existingProduct);
        }else {
            throw new ProductException("product not found");
        }
    }

    @Override
    public ProductResponseDto getProductById(Long id) {
        Optional<Product> product = productRepository.findById(id);
        if(product.isPresent()){
            return productResponseDTOMapper.toDto(product.get());
        }else {
            throw new ProductException("product Id not found " + id);
        }
    }

    @Override
    public Page<ProductResponseDto> filterProducts(Pageable pageable, List<Long> categoryIds, Double minPrice, Double maxPrice, String brand, Boolean newArrival, String color, String material, String name) {
        ProductSearchRequest searchRequest = new ProductSearchRequest();
        List<Category> cats  = new ArrayList<Category>();
        if(categoryIds != null){
            for(Long categoryId : categoryIds){
                Optional<Category> catOptional = categoryRepo.findById(categoryId);
                if(catOptional.isPresent()){
                    Category category = catOptional.get();
                    cats.add(category);
                }
            }
        }

        searchRequest.setCategories(cats);
        searchRequest.setMinPrice(minPrice);
        searchRequest.setMaxPrice(maxPrice);
        searchRequest.setBrand(brand);
        searchRequest.setNewArrival(newArrival);
        searchRequest.setColor(color);
        searchRequest.setMaterial(material);
        searchRequest.setName(name);
        return customProductRepository.searchProduct(searchRequest, pageable).map(productResponseDTOMapper::toDto);
    }

    @Override
    public List<ReviewDTO> getReviewsByProductID(Long id) {
        List<Review> reviews = productRepository.getReviewsByProductID(id);
        return reviews.stream()
                .map(reviewDTOMapper::apply)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public ProductResponseDto setProductStatus(Long id, ProductStatusUpdateDTO status) {
        Optional<Product> productOptional = productRepository.findById(id);
        if (productOptional.isPresent()) {
            Product existingProduct = productOptional.get();
            existingProduct.setStatus(status.getStatus());
            productRepository.save(existingProduct);
            return productResponseDTOMapper.toDto(existingProduct);
        } else {
           throw new ProductException("product not found " + id);
        }
    }

    @Override
    public List<ProductResponseDto> getProductsBySellerId(Principal principal) {
        String email = principal.getName();
        Optional<User> userOptional = userRepository.findByEmail(email);
        if(userOptional.isPresent()){
            User user = userOptional.get();
            List<Product> products = productRepository.findAllProductBySellerId(user.getId());

            return products.stream().map(productResponseDTOMapper::toDto).collect(Collectors.toList());
        } else {
            throw new ProductException("Invalid Seller");

        }
    }

    @Override
    public Page<ProductResponseDto> getAllProductsForAdmin(Pageable pageable) {
        return productRepository.findAll(pageable).map(productResponseDTOMapper::toDto);
    }

}
