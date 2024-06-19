package com.spa.ecommerce.review;

import com.spa.ecommerce.category.Category;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService{

    @Autowired
    private final ReviewRepository reviewRepository;

    @Autowired
    private final ReviewDTOMapper reviewDTOMapper;
    @Override
    public Collection<ReviewDTO> getAll() {
        return reviewRepository.findAll().stream().map(reviewDTOMapper)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<ReviewDTO> get(Long id) {
        return reviewRepository.findById(id).map(reviewDTOMapper);
    }

    @Override
    public Optional<ReviewDTO> save(ReviewDTO reviewDTO) {
        Review review = new Review();
        review.setId(reviewDTO.getId());
        review.setComment(reviewDTO.getComment());
        //review.setBuyer(reviewDTO.getUser());
        review.setProduct(reviewDTO.getProduct());
        review.setRating(reviewDTO.getRating());
        return Optional.empty();
    }

    @Override
    public Optional<ReviewDTO> update(Long id, ReviewDTO updateReview) {
        Optional<Review> existingReviewOpt = reviewRepository.findById(id);
        if (existingReviewOpt.isPresent()) {
            Review existingReview = existingReviewOpt.get();
            existingReview.setRating(updateReview.getRating());
            reviewRepository.save(existingReview);

            return Optional.of(reviewDTOMapper.apply(existingReview));
        } else {
            return Optional.empty();
        }
    }

    @Override
    public Optional<ReviewDTO> delete(Long id, ReviewDTO entity) {
        Optional<Review> existingReviewOpt = reviewRepository.findById(id);
        if (existingReviewOpt.isPresent()) {
            reviewRepository.deleteById(id);
            return Optional.of(reviewDTOMapper.apply(existingReviewOpt.get()));
        } else {
            return Optional.empty();
        }
    }
}
