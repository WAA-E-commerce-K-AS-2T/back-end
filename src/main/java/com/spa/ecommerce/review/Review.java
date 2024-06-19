package com.spa.ecommerce.review;

import com.spa.ecommerce.common.Auditable;
import com.spa.ecommerce.product.entity.Product;
import com.spa.ecommerce.user.Buyer;
import jakarta.persistence.*;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Entity
@RequiredArgsConstructor
@Data
public class Review extends Auditable<Buyer> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private double rating;
    private String comment;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;
}
