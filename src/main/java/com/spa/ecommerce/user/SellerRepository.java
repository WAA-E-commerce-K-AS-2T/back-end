package com.spa.ecommerce.user;

import com.spa.ecommerce.order.orderitem.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SellerRepository extends JpaRepository<Seller, Long> {

    @Query("SELECT oi FROM OrderItem oi JOIN oi.order o JOIN oi.product p WHERE p.seller.id = :sellerId")
    Optional<List<OrderItem>> findOrderItemsByUserId(@Param("sellerId") Long sellerId);
}
