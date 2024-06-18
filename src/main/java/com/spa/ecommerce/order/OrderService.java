package com.spa.ecommerce.order;

import com.spa.ecommerce.order.dto.OrderDTO;

import java.util.List;

public interface OrderService {
    public List<OrderDTO> findAll();
    public OrderDTO save(Order order);
    public OrderDTO findById(int id);
    public void update(int orderId, Order order);
    public void delete (int orderId);
}
