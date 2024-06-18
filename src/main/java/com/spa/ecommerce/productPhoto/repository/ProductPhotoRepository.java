package com.spa.ecommerce.productPhoto.repository;

import com.spa.ecommerce.productPhoto.entity.ProductPhoto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductPhotoRepository extends JpaRepository<ProductPhoto, Long> {
    void deleteByProductId(Long productId);
}
