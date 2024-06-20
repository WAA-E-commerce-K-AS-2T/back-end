package com.spa.ecommerce.product.service;

import com.spa.ecommerce.product.dto.ProductDTO;
import com.spa.ecommerce.product.dto.ProductResponseDto;
import com.spa.ecommerce.product.dto.ProductStatusUpdateDTO;
import com.spa.ecommerce.review.ReviewDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

public interface ProductService {
    ProductResponseDto saveProduct(Principal principal, ProductDTO productDTO, MultipartFile [] photos);

    Page<ProductResponseDto> getAllProducts(Pageable pageable);

    ProductResponseDto deleteById(Long id);

    ProductResponseDto updateProduct(Long id, ProductDTO productDTO, MultipartFile[] photos);

    ProductResponseDto getProductById(Long id);

    Page<ProductResponseDto> filterProducts(Pageable pageable, List<Long> categories, Double minPrice, Double maxPrice, String brand, Boolean newArrival, String size, String color, String name);

    public List<ReviewDTO> getReviewsByProductID(Long id);

    public ProductResponseDto setProductStatus(Long id, ProductStatusUpdateDTO status);

    public List<ProductResponseDto> getProductsBySellerId(Principal principal);
}
