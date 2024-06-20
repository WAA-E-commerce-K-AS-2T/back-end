package com.spa.ecommerce.review;

import com.spa.ecommerce.product.dto.ProductDTOMapper;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.time.LocalDateTime;
import java.util.function.Function;

@Service
public class ReviewDTOMapper implements Function<Review, ReviewDTO> {

    @Override
    public ReviewDTO apply(Review review) {
        LocalDateTime dateTime = null;
        if (review.getCreatedDate() != null){
           dateTime = review.getCreatedDate();
        }

        String fullName = null;
        if (review.getCreatedBy()!= null){
            fullName = review.getCreatedBy().getFullName();
        }

        return new ReviewDTO (
                review.getId(),
                review.getRating(),
                review.getComment(),
                review.getProduct().getId(),
                dateTime,
                fullName
                );
    }
}
