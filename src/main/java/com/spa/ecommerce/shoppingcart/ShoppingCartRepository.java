package com.spa.ecommerce.shoppingcart;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ShoppingCartRepository extends JpaRepository<ShoppingCart, Long> {

    ShoppingCart findByBuyerId(Long buyerId);
}
