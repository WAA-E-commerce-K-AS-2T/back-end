package com.spa.ecommerce.product.dto;

import com.spa.ecommerce.product.entity.Product;
import com.spa.ecommerce.productPhoto.dto.ProductPhotoDTO;
import com.spa.ecommerce.productPhoto.dto.ProductPhotoDtoMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductDTOMapper implements Function<Product, ProductDTO> {
    private final ProductPhotoDtoMapper productPhotoDtoMapper;
    @Override
    public ProductDTO apply(Product product) {
        ProductDTO productDTO = new ProductDTO();
        BeanUtils.copyProperties(product, productDTO);
        List<ProductPhotoDTO> productDtoPhotos = product.getProductPhotos().stream().map(productPhotoDtoMapper).collect(Collectors.toList());
        productDTO.setProductPhotos(productDtoPhotos);
        productDTO.setProductStatus(product.getStatus().getStatus());
        return productDTO;
    }
}
