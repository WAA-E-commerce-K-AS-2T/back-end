package com.spa.ecommerce.review;

import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class ReviewDTOMapper implements Function<Review, ReviewDTO> {

    @Override
    public ReviewDTO apply(Review review) {
        return new ReviewDTO (
                review.getId(),
                review.getRating(),
                review.getComment(),
                review.getProduct()
                );
    }
}
