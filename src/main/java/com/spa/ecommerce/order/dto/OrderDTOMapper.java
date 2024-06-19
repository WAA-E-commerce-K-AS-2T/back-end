package com.spa.ecommerce.order.dto;

import com.spa.ecommerce.order.Order;
import com.spa.ecommerce.order.orderitem.dto.OrderItemDTOMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class OrderDTOMapper implements Function<Order, OrderDTO> {

    private final OrderItemDTOMapper orderItemDTOMapper;

    public OrderDTOMapper(OrderItemDTOMapper orderItemDTOMapper) {
        this.orderItemDTOMapper = orderItemDTOMapper;
    }

    @Override
    public OrderDTO apply(Order order) {
        OrderDTO dto = new OrderDTO();
        BeanUtils.copyProperties(order, dto);
        dto.setOrderItems(order.getOrderItems().stream().map(orderItemDTOMapper).collect(Collectors.toList()));

        return dto;
    }

    @Override
    public <V> Function<V, OrderDTO> compose(Function<? super V, ? extends Order> before) {
        return Function.super.compose(before);
    }

    @Override
    public <V> Function<Order, V> andThen(Function<? super OrderDTO, ? extends V> after) {
        return Function.super.andThen(after);
    }
}
