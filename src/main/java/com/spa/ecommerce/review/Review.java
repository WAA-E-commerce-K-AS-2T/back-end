package com.spa.ecommerce.review;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.spa.ecommerce.common.Auditable;
import com.spa.ecommerce.product.entity.Product;
import com.spa.ecommerce.user.Buyer;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Entity
@RequiredArgsConstructor
@Getter
@Setter
public class Review extends Auditable<Buyer> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private double rating;
    private String comment;

    @ManyToOne
    @JoinColumn(name = "product_id")

    @JsonBackReference(value = "product-review")
    private Product product;
}
