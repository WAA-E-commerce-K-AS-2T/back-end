package com.spa.ecommerce.review;

import com.spa.ecommerce.product.dto.ProductDTOMapper;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
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
                review.getProduct().getId()
                );
    }
}
