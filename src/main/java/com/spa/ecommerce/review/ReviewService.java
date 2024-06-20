package com.spa.ecommerce.review;

import com.spa.ecommerce.common.GeneralService;

import java.util.Collection;
import java.util.Optional;

public interface ReviewService {
    public Collection<ReviewDTO> getAllReviews();
    public Optional<ReviewDTO> getReviewById(Long id);
    public Optional<ReviewDTO> save(Long productId, ReviewDTO reviewDTO);
    public Optional<ReviewDTO> update(Long productId, Long reviewId, ReviewDTO updateReview);
    public Optional<ReviewDTO> delete(Long id);
}
