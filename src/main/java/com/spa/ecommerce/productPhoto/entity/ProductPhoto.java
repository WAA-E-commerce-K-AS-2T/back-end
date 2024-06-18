package com.spa.ecommerce.productPhoto.entity;

import com.spa.ecommerce.product.entity.Product;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class ProductPhoto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String imageUrl;
    private String imageId;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;
}
