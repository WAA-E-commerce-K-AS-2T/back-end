package com.spa.ecommerce.product.service;

import com.spa.ecommerce.product.dto.ProductDTO;
import com.spa.ecommerce.product.dto.ProductStatusUpdateDTO;
import com.spa.ecommerce.review.ReviewDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

public interface ProductService {
    Optional<ProductDTO> saveProduct(Principal principal, ProductDTO productDTO, MultipartFile [] photos);

    Page<ProductDTO> getAllProducts(Pageable pageable);

    Optional<ProductDTO> deleteById(Long id);

    Optional<ProductDTO> updateProduct(Long id, ProductDTO productDTO, MultipartFile[] photos);

    Optional<ProductDTO> getProductById(Long id);

    Page<ProductDTO> filterProducts(Pageable pageable, List<Long> categories, Double minPrice, Double maxPrice, String brand, Boolean newArrival, String size, String color);

    public List<ReviewDTO> getReviewsByProductID(Long id);

    public Optional<ProductDTO> setProductStatus(Long id, ProductStatusUpdateDTO status);
}
