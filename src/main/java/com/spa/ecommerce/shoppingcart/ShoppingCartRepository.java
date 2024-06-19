package com.spa.ecommerce.shoppingcart;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ShoppingCartRepository extends JpaRepository<ShoppingCart, Integer> {
    Optional<ShoppingCart> findByBuyerId(Long buyerId);
}
