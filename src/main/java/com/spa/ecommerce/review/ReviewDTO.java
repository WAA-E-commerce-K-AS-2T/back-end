package com.spa.ecommerce.review;

import com.spa.ecommerce.product.entity.Product;
import com.spa.ecommerce.user.User;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class ReviewDTO {
    private Long id;
    private double rating;
    private String comment;
    private Product product;
    private User user;

    public ReviewDTO(Long id, double rating, String comment, Product product, User user) {
        this.id = id;
        this.rating = rating;
        this.comment = comment;
        this.product = product;
        this.user = user;
    }
}
