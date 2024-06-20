package com.spa.ecommerce.product.repository;

import com.spa.ecommerce.product.entity.Product;
import com.spa.ecommerce.review.Review;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long>, JpaSpecificationExecutor<Product> {
    Page<Product> findAll(Pageable pageable);

    @Query ("SELECT p.reviews FROM Product p where p.id = :id")
    public List<Review> getReviewsByProductID(Long id);

    @Query("SELECT p FROM Product p WHERE p.seller.id = :sellerId")
    List<Product> findAllProductBySellerId(@Param("sellerId") Long sellerId);
}
