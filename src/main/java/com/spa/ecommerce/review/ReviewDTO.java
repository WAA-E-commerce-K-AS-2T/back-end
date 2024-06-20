package com.spa.ecommerce.review;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.spa.ecommerce.product.dto.ProductDTO;
import com.spa.ecommerce.product.entity.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class ReviewDTO {
    private Long id;
    private double rating;
    private String comment;

    private Long productId;

    private LocalDateTime createdDate;
    private String createdBy;

}
