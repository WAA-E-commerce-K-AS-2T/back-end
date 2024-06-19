package com.spa.ecommerce.review;

import com.spa.ecommerce.product.dto.ProductDTOMapper;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class ReviewDTOMapper implements Function<Review, ReviewDTO> {

    private final ProductDTOMapper productDTOMapper;

    public ReviewDTOMapper(ProductDTOMapper productDTOMapper) {
        this.productDTOMapper = productDTOMapper;
    }

    @Override
    public ReviewDTO apply(Review review) {
        return new ReviewDTO (
                review.getId(),
                review.getRating(),
                review.getComment(),
                productDTOMapper.apply(review.getProduct())
                );
    }
}
