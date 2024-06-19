package com.spa.ecommerce.product.dto;

import com.spa.ecommerce.category.Category;
import com.spa.ecommerce.user.User;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Data
public class ProductSearchRequest {
    private List<Category> categories;
    private Double minPrice;
    private Double maxPrice;
    private String brand;
    private Boolean newArrival;
    private String color;
    private String material;
    //private int reviews;
}
