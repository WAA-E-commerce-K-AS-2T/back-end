package com.spa.ecommerce.orderitem;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<OrderItem, Integer> {
}
