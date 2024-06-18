package com.spa.ecommerce.productPhoto.dto;

import com.spa.ecommerce.productPhoto.entity.ProductPhoto;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class ProductPhotoDtoMapper implements Function<ProductPhoto, ProductPhotoDTO> {
    @Override
    public ProductPhotoDTO apply(ProductPhoto productPhoto) {
        return new ProductPhotoDTO(
                productPhoto.getId(),
                productPhoto.getName(),
                productPhoto.getImageUrl(),
                productPhoto.getImageId()
        );
    }
}
