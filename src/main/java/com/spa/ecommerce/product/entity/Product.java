package com.spa.ecommerce.product.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.spa.ecommerce.category.Category;
import com.spa.ecommerce.common.ProductStatusEnum;
import com.spa.ecommerce.productPhoto.entity.ProductPhoto;
import com.spa.ecommerce.review.Review;
import com.spa.ecommerce.user.User;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToMany
    @JoinTable(
            name = "product_category",
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id")
    )
    private List<Category> categories;

    private String name;

    private String description;

    private double price;

    private String brand;

    private String productSize;

    private String color;
    private String material;

    @Enumerated(EnumType.STRING)
    private ProductStatusEnum status;

    private int inStock;

    private LocalDate postedDate;

    @ManyToOne
    @JoinColumn(name = "seller_id")
    private User seller;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private List<ProductPhoto> productPhotos = new ArrayList<>();

    @OneToMany(mappedBy = "product",cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonBackReference
    private List<Review> reviews;

    private int timesBought;

    public void addProductPhoto(ProductPhoto productPhoto){
        this.productPhotos.add(productPhoto);
    }

}
