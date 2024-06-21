package com.spa.ecommerce.product.dto;

import com.spa.ecommerce.category.Category;
import com.spa.ecommerce.product.entity.Product;
import com.spa.ecommerce.productPhoto.dto.ProductPhotoDTO;
import com.spa.ecommerce.productPhoto.dto.ProductPhotoDtoMapper;
import com.spa.ecommerce.review.ReviewDTO;
import com.spa.ecommerce.review.ReviewDTOMapper;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class ProductResponseDTOMapper {

    private final ReviewDTOMapper reviewDTOMapper;
    private final ProductPhotoDtoMapper productPhotoDtoMapper;


    public ProductResponseDto toDto(Product product) {
        ProductResponseDto productResponseDto = new ProductResponseDto();
        BeanUtils.copyProperties(product, productResponseDto);
        productResponseDto.setProductStatus(product.getStatus().getStatus());
        List<Category> cats = product.getCategories();
        List<Long> catIds = cats.stream().map(Category::getId).collect(Collectors.toList());
        productResponseDto.setCategoryIds(catIds);
        List<ProductPhotoDTO> productDtoPhotos = product.getProductPhotos().stream().map(productPhotoDtoMapper).collect(Collectors.toList());
        productResponseDto.setProductPhotos(productDtoPhotos);
        List<ReviewDTO> reviews = product.getReviews().stream().map(reviewDTOMapper).collect(Collectors.toList());
        double averageReview = reviews.stream().mapToDouble(ReviewDTO::getRating).average().orElse(0);
        productResponseDto.setReviews(reviews);
        productResponseDto.setAverageRating(averageReview);
        productResponseDto.setTotalReviews(reviews.size());
        return productResponseDto;
    }
}
