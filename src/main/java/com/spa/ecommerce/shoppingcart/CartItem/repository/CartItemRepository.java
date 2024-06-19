package com.spa.ecommerce.shoppingcart.CartItem.repository;

import com.spa.ecommerce.shoppingcart.CartItem.entity.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {
}
