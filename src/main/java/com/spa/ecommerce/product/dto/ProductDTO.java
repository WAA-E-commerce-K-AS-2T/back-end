package com.spa.ecommerce.product.dto;

import com.spa.ecommerce.productPhoto.dto.ProductPhotoDTO;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ProductDTO {
    private long id;
    private List<Long> categoryIds;
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
}
