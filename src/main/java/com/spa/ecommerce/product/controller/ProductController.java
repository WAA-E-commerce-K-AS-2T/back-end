package com.spa.ecommerce.product.controller;

import com.spa.ecommerce.common.Constant;
import com.spa.ecommerce.product.dto.ProductDTO;
import com.spa.ecommerce.product.dto.ProductStatusUpdateDTO;
import com.spa.ecommerce.product.entity.Product;
import com.spa.ecommerce.product.service.ProductService;
import com.spa.ecommerce.review.ReviewDTO;
import com.spa.ecommerce.review.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(Constant.PRODUCT_URL_PREFIX)
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;
    private final ReviewService reviewService;

    @PostMapping
    public ResponseEntity<ProductDTO> saveProduct(Principal principal, @RequestPart(name = "product") ProductDTO product, @RequestPart(name = "photos") MultipartFile[] photos) {
        Optional<ProductDTO> productDTO = productService.saveProduct(principal, product, photos);
        return productDTO
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).build());

    }
    @PutMapping("/{productId}")
    public ResponseEntity<ProductDTO> updateProduct(@PathVariable Long productId, @RequestPart(name = "product") ProductDTO product, @RequestPart(name = "photos") MultipartFile[] photos) {
        Optional<ProductDTO> productDTO = productService.updateProduct(productId, product, photos);
        return productDTO
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).build());
    }

    @GetMapping
    public ResponseEntity<Page<ProductDTO>> getAllProducts(Pageable pageable) {
        return new ResponseEntity<>(productService.getAllProducts(pageable), HttpStatus.OK);
    }

    @GetMapping("/{productId}")
    public ResponseEntity<ProductDTO> getProductById(@PathVariable Long productId) {
        return productService.getProductById(productId)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @DeleteMapping("/{productId}")
    public ResponseEntity<ProductDTO> deleteProductById(@PathVariable Long productId) {
        return  productService.deleteById(productId)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).build());
    }

    @GetMapping("/filter")
    public ResponseEntity<Page<ProductDTO>> filterProduct(@RequestParam(name = "categories", required = false) List<Long> categories,
                                                          @RequestParam(name = "minPrice", required = false) Double minPrice,
                                                          @RequestParam(name = "maxPrice", required = false) Double maxPrice,
                                                          @RequestParam(name = "brand", required = false) String brand,
                                                          @RequestParam(name = "newArrival", required = false) Boolean newArrival,
                                                          @RequestParam(name = "color", required = false) String color,
                                                          @RequestParam(name = "material", required = false) String material,
                                                          Pageable pageable
                                                          ){
        return new ResponseEntity<>(productService.filterProducts(pageable, categories, minPrice, maxPrice, brand, newArrival, color, material), HttpStatus.OK);
    }

    @GetMapping("/{productId}/reviews")
    public ResponseEntity<List<ReviewDTO>> getReviewsByProductID(@PathVariable("productId") Long id) {
        List<ReviewDTO> reviews = productService.getReviewsByProductID(id);
        return new ResponseEntity<>(reviews, HttpStatus.OK);
    }

    // get details review

    @PostMapping("/{productId}/reviews")
    public ResponseEntity<ReviewDTO> saveReview(@PathVariable Long productId, @RequestBody ReviewDTO reviewDTO) {
        Optional<ProductDTO> product = productService.getProductById(productId);
        Optional<ReviewDTO> savedReview = Optional.empty();
        if (product.isPresent()){
            reviewDTO.setProduct(product.get());
            savedReview = reviewService.save(reviewDTO);
        }

        return savedReview.map(ResponseEntity::ok)
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    // edit review
    @PutMapping("/{productId}/reviews/{reviewId}")
    public ResponseEntity<ReviewDTO> updateReview(@PathVariable Long productId, @PathVariable Long reviewId, @RequestBody ReviewDTO reviewDTO) {
        Optional<ProductDTO> product = productService.getProductById(productId);
        Optional<ReviewDTO> updatedReview = Optional.empty();
        if (product.isPresent()) {
            reviewDTO.setProduct(product.get());
            updatedReview = reviewService.update(reviewId, reviewDTO);
        }

        return updatedReview.map(ResponseEntity::ok)
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    //delete review
    @DeleteMapping("/{productId}/reviews/{reviewId}")
    public ResponseEntity<ReviewDTO> deleteReview(@PathVariable Long productId, @PathVariable Long reviewId) {
        Optional<ReviewDTO> deletedReview = reviewService.delete(reviewId);
        return deletedReview.map(ResponseEntity::ok)
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }


    //approve product
    @PutMapping("/{productId}/set-status")
    public  ResponseEntity<ProductDTO> setProductStatus(@PathVariable Long productId, @RequestBody ProductStatusUpdateDTO status){
        Optional<ProductDTO> productDTO = productService.setProductStatus(productId, status);
        return productDTO.map(ResponseEntity::ok)
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

}
