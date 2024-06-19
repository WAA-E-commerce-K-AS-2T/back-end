package com.spa.ecommerce.review;

import com.spa.ecommerce.product.dto.ProductDTO;
import com.spa.ecommerce.product.entity.Product;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class ReviewDTO {
    private Long id;
    private double rating;
    private String comment;
    private ProductDTO product;

    public ReviewDTO(Long id, double rating, String comment, ProductDTO product) {
        this.id = id;
        this.rating = rating;
        this.comment = comment;
        this.product = product;
    }
}
