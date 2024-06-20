package com.spa.ecommerce.product.dto;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.spa.ecommerce.productPhoto.dto.ProductPhotoDTO;
import com.spa.ecommerce.review.ReviewDTO;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ProductResponseDto {
    private long id;
    private List<Long> categoryIds = new ArrayList<>();
    private String name;
    private String description;
    private double price;
    private String brand;
    private String color;
    private String material;
    private String productSize;
    private int inStock;
    private String productStatus;
    private List<ProductPhotoDTO> productPhotos;

    @JsonManagedReference(value = "product-review")
    private List<ReviewDTO> reviews;
    private double averageRating;
    private int totalReviews;
}
