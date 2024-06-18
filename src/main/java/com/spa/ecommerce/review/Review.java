package com.spa.ecommerce.review;

import com.spa.ecommerce.product.entity.Product;
import com.spa.ecommerce.user.User;
import jakarta.persistence.*;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Entity
@RequiredArgsConstructor
@Data
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private double rating;
    private String comment;

    @ManyToOne
    private Product product;

    @OneToOne
    private User user;
}
