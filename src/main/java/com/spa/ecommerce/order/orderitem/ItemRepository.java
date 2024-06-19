package com.spa.ecommerce.order.orderitem;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<OrderItem, Long> {
}
