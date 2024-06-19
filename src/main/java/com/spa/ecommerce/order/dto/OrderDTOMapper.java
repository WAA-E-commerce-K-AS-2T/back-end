package com.spa.ecommerce.order.dto;

import com.spa.ecommerce.order.Order;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class OrderDTOMapper implements Function<Order, OrderDTO> {

    @Override
    public OrderDTO apply(Order order) {
        OrderDTO dto = new OrderDTO();
        BeanUtils.copyProperties(order, dto);
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
