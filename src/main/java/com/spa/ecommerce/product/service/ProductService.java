package com.spa.ecommerce.product.service;

import com.spa.ecommerce.product.dto.ProductDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    Optional<ProductDTO> saveProduct(Long sellerId, ProductDTO productDTO, MultipartFile [] photos);

    Page<ProductDTO> getAllProducts(Pageable pageable);

    Optional<ProductDTO> deleteById(Long id);

    Optional<ProductDTO> updateProduct(Long id, ProductDTO productDTO, MultipartFile[] photos);

    Optional<ProductDTO> getProductById(Long id);

    Page<ProductDTO> filterProducts(Pageable pageable, List<Long> categories, Double minPrice, Double maxPrice, String brand, Boolean newArrival, String size, String color);

}
