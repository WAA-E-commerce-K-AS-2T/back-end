package com.spa.ecommerce.user;

import com.spa.ecommerce.common.Constant;
import com.spa.ecommerce.product.dto.ProductResponseDto;
import com.spa.ecommerce.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(Constant.ADMIN_PRODUCT_URL_PREFIX)
@RequiredArgsConstructor
public class AdminController {
    private final ProductService productService;

    @GetMapping("/products")
    public ResponseEntity<Page<ProductResponseDto>> getAllAdminProducts(Pageable pageable) {
        Page<ProductResponseDto> productDTO = productService.getAllProductsForAdmin(pageable);
        return new ResponseEntity<>(productDTO, HttpStatus.OK);
    }

}
