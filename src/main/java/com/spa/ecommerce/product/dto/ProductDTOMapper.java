package com.spa.ecommerce.product.dto;

import com.spa.ecommerce.category.Category;
import com.spa.ecommerce.product.entity.Product;
import com.spa.ecommerce.productPhoto.dto.ProductPhotoDTO;
import com.spa.ecommerce.productPhoto.dto.ProductPhotoDtoMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
        List<Category> cats = product.getCategories();
        List<Long> catIds = cats.stream().map(Category::getId).collect(Collectors.toList());
        productDTO.setCategoryIds(catIds);
        List<ProductPhotoDTO> productDtoPhotos = product.getProductPhotos().stream().map(productPhotoDtoMapper).collect(Collectors.toList());
        productDTO.setProductPhotos(productDtoPhotos);
        productDTO.setProductStatus(product.getStatus().getStatus());
        return productDTO;
    }
}
