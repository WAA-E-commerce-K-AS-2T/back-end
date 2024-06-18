package com.spa.ecommerce.user.controller;

import com.spa.ecommerce.common.Constant;
import com.spa.ecommerce.product.dto.ProductDTO;
import com.spa.ecommerce.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;

@RestController
@RequestMapping(Constant.SELLER_URL_PREFIX)
@RequiredArgsConstructor
public class SellerController {

    private final ProductService productService;

    @PostMapping("/{sellerId}/products")
    public ResponseEntity<ProductDTO> saveProduct(@PathVariable Long sellerId , @RequestPart(name = "product") ProductDTO product, @RequestPart(name = "photos") MultipartFile[] photos) {
        Optional<ProductDTO> productDTO = productService.saveProduct(sellerId, product, photos);
        return productDTO
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).build());

    }

    @PutMapping("/{sellerId}/products/{productId}")
    public ResponseEntity<ProductDTO> updateProduct(@PathVariable Long sellerId , @PathVariable Long productId, @RequestPart(name = "product") ProductDTO product, @RequestPart(name = "photos") MultipartFile[] photos) {
        Optional<ProductDTO> productDTO = productService.updateProduct(productId, product, photos);
        return productDTO
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).build());
    }
}
