package com.spa.ecommerce.review;

import com.spa.ecommerce.common.Constant;
import com.spa.ecommerce.user.UserDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Optional;

@RestController
@RequestMapping(Constant.REVIEW_URL_PREFIX)
@RequiredArgsConstructor
public class ReviewController {

    @Autowired
    private final ReviewService reviewService;

    @GetMapping
    public ResponseEntity<Collection<ReviewDTO>> getAllReviews() {
        return new ResponseEntity<>(reviewService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReviewDTO> getReview(@PathVariable("id") Long id) {
        return reviewService.get(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @PostMapping
    public ResponseEntity<ReviewDTO> saveReview(@RequestBody ReviewDTO reviewDTO) {
        Optional<ReviewDTO> savedReview = reviewService.save(reviewDTO);
        return savedReview.map(ResponseEntity::ok)
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<ReviewDTO> updateReview(@PathVariable Long id, @RequestBody ReviewDTO reviewDTO) {
        Optional<ReviewDTO> updatedReviewOpt = reviewService.update(id, reviewDTO);
        return updatedReviewOpt
                .map(updatedReview -> new ResponseEntity<>(updatedReview, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ReviewDTO> deleteReview(@PathVariable Long id, @RequestBody ReviewDTO reviewDTO) {
        Optional<ReviewDTO> deletedReviewOpt = reviewService.delete(id, reviewDTO);
        return deletedReviewOpt
                .map(deletedReview -> new ResponseEntity<>(deletedReview, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}


