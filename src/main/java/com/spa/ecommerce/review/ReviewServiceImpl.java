package com.spa.ecommerce.review;

import com.spa.ecommerce.category.Category;
import com.spa.ecommerce.product.entity.Product;
import com.spa.ecommerce.product.repository.ProductRepository;
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
    private ProductRepository productRepository;
    @Autowired
    private final ReviewDTOMapper reviewDTOMapper;
    @Override
    public Collection<ReviewDTO> getAllReviews() {
        return reviewRepository.findAll().stream().map(reviewDTOMapper)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<ReviewDTO> getReviewById(Long id) {
        return reviewRepository.findById(id).map(reviewDTOMapper);
    }

    @Override
    public Optional<ReviewDTO> save(ReviewDTO reviewDTO) {
        Optional<Product> product= productRepository.findById(reviewDTO.getProduct().getId());
        Review review = new Review();
        review.setId(reviewDTO.getId());
        review.setComment(reviewDTO.getComment());
        if (product.isPresent()) {
            review.setProduct(product.get());
        }
        review.setRating(reviewDTO.getRating());

        review = reviewRepository.save(review);

        return Optional.of(reviewDTOMapper.apply(review));
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
    public Optional<ReviewDTO> delete(Long id) {
        Optional<Review> existingReviewOpt = reviewRepository.findById(id);
        if (existingReviewOpt.isPresent()) {
            reviewRepository.deleteById(id);
            return Optional.of(reviewDTOMapper.apply(existingReviewOpt.get()));
        } else {
            return Optional.empty();
        }
    }
}
