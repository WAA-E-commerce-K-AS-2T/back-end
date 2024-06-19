package com.spa.ecommerce.product.dto;

import lombok.Data;

@Data
public class ProductResponseDto {
    private long id;
    private String name;
    private double price;
}
