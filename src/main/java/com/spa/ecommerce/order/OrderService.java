package com.spa.ecommerce.order;

import com.spa.ecommerce.order.dto.OrderDTO;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

public interface OrderService {
    public List<OrderDTO> findAll(Principal principal);
    public OrderDTO findById(long id);
//    public Optional<OrderDTO> update(long orderId, Status status);
    public OrderDTO placeOrder(Principal principal, OrderDTO orderDTO);




    OrderDTO cancelOrder(Principal principal, long orderId);

    OrderDTO updateOrderStatus(Principal principal, long orderId);
}
