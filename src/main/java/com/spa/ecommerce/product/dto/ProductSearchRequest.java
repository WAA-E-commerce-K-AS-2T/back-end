package com.spa.ecommerce.product.dto;

import com.spa.ecommerce.user.User;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
public class ProductSearchRequest {
    private String category;
    private Double minPrice;
    private Double maxPrice;
    private String brand;
    private Boolean newArrival;
    private String color;
    private String material;
    //private int reviews;
}
