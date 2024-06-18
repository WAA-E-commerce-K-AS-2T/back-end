package com.spa.ecommerce.shoppingCartItem;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CartItemRepository extends JpaRepository<CartItem, Integer> {
}
