package com.spa.ecommerce.orderitem;

import com.spa.ecommerce.orderitem.dto.OrderItemDTO;

import java.util.List;

public interface ItemService {
    OrderItemDTO save(OrderItem orderItem);
    OrderItemDTO findById(int id);
    void update(int id, OrderItem orderItem);

    void delete(int id);

    List<OrderItemDTO> findAll();
}


