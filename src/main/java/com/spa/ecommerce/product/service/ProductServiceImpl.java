package com.spa.ecommerce.product.service;

import com.spa.ecommerce.common.ProductStatusEnum;
import com.spa.ecommerce.product.dto.ProductDTO;
import com.spa.ecommerce.product.dto.ProductDTOMapper;
import com.spa.ecommerce.product.dto.ProductSearchRequest;
import com.spa.ecommerce.product.entity.Product;
import com.spa.ecommerce.product.repository.CustomProductRepository;
import com.spa.ecommerce.product.repository.ProductRepository;
import com.spa.ecommerce.productPhoto.entity.ProductPhoto;
import com.spa.ecommerce.productPhoto.repository.ProductPhotoRepository;
import com.spa.ecommerce.productPhoto.service.CloudinaryServiceImpl;
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
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final UserRepository userRepository;
    private final ProductRepository productRepository;
    private final ProductDTOMapper productDTOMapper;
    private final CloudinaryServiceImpl cloudinaryService;
    private final ProductPhotoRepository productPhotoRepository;
    private final CustomProductRepository customProductRepository;

    @Override
    public Optional<ProductDTO> saveProduct(Long sellerId, ProductDTO productDTO, MultipartFile[] photos) {
        Optional<User> OptUser = userRepository.findById(sellerId);
        if(OptUser.isPresent()){
            User user = OptUser.get();
            Product product = new Product();
            BeanUtils.copyProperties(productDTO, product);
            product.setStatus(ProductStatusEnum.IN_REVIEW);
            product.setSeller(user);
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
            productRepository.save(product);
            return Optional.of(productDTOMapper.apply(product));
        } else{
            return Optional.empty();
        }
    }

    @Override
    public Page<ProductDTO> getAllProducts(Pageable pageable) {
        return productRepository.findAll(pageable).map(productDTOMapper);
    }

    @Override
    public Optional<ProductDTO> deleteById(Long id) {
        Optional<Product> product = productRepository.findById(id);
        if(product.isPresent()){
            productRepository.deleteById(id);
            return Optional.of(productDTOMapper.apply(product.get()));
        }else {
            return Optional.empty();
        }
    }

    @Override
    @Transactional
    public Optional<ProductDTO> updateProduct(Long id, ProductDTO productDTO, MultipartFile[] photos) {
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
                    existingProduct.addProductPhoto(productPhoto);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }

            ProductStatusEnum status = existingProduct.getStatus();
            List<ProductPhoto> productPhoto = existingProduct.getProductPhotos();
            existingProduct.setCategory(productDTO.getCategory());
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
           return Optional.of(productDTOMapper.apply(existingProduct));
        }else {
            return Optional.empty();
        }
    }

    @Override
    public Optional<ProductDTO> getProductById(Long id) {
        Optional<Product> product = productRepository.findById(id);
        if(product.isPresent()){
            return Optional.of(productDTOMapper.apply(product.get()));
        }else {
            return Optional.empty();
        }
    }

    @Override
    public Page<ProductDTO> filterProducts(Pageable pageable, String category, Double minPrice, Double maxPrice, String brand, Boolean newArrival, String color, String material) {
        ProductSearchRequest searchRequest = new ProductSearchRequest();
        searchRequest.setCategory(category);
        searchRequest.setMinPrice(minPrice);
        searchRequest.setMaxPrice(maxPrice);
        searchRequest.setBrand(brand);
        searchRequest.setNewArrival(newArrival);
        searchRequest.setColor(color);
        searchRequest.setMaterial(material);
        return customProductRepository.searchProduct(searchRequest, pageable).map(productDTOMapper);
    }
}
