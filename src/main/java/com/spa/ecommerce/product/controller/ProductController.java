package com.spa.ecommerce.product.controller;

import com.spa.ecommerce.common.Constant;
import com.spa.ecommerce.product.dto.ProductDTO;
import com.spa.ecommerce.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(Constant.PRODUCT_URL_PREFIX)
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;


    @GetMapping
    public ResponseEntity<Page<ProductDTO>> getAllProducts(Pageable pageable) {
        return new ResponseEntity<>(productService.getAllProducts(pageable), HttpStatus.OK);
    }

    @GetMapping("/{productId}")
    public ResponseEntity<ProductDTO> getProductById(@PathVariable Long productId) {
        return productService.getProductById(productId)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @DeleteMapping("/{productId}")
    public ResponseEntity<ProductDTO> deleteProductById(@PathVariable Long productId) {
        return  productService.deleteById(productId)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).build());
    }

    @GetMapping("/filter")
    public ResponseEntity<Page<ProductDTO>> filterProduct(@RequestParam(name = "category", required = false) String category,
                                                          @RequestParam(name = "minPrice", required = false) Double minPrice,
                                                          @RequestParam(name = "maxPrice", required = false) Double maxPrice,
                                                          @RequestParam(name = "brand", required = false) String brand,
                                                          @RequestParam(name = "newArrival", required = false) Boolean newArrival,
                                                          @RequestParam(name = "color", required = false) String color,
                                                          @RequestParam(name = "material", required = false) String material,
                                                          Pageable pageable
                                                          ){
        return new ResponseEntity<>(productService.filterProducts(pageable, category, minPrice, maxPrice, brand, newArrival, color, material), HttpStatus.OK);
    }
}
