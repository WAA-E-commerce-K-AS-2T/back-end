package com.spa.ecommerce.productPhoto.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductPhotoDTO {
    private Long id;
    private String name;
    private String imageUrl;
    private String imageId;
}
