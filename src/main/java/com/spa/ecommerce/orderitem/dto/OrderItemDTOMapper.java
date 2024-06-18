package com.spa.ecommerce.orderitem.dto;


import com.spa.ecommerce.orderitem.OrderItem;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class OrderItemDTOMapper implements Function<OrderItem, OrderItemDTO> {

    @Override
    public OrderItemDTO apply(OrderItem item) {
        return new OrderItemDTO(
                item.getItemId(),
                item.getQuantity()
        );
    }

    @Override
    public <V> Function<V, OrderItemDTO> compose(Function<? super V, ? extends OrderItem> before) {
        return Function.super.compose(before);
    }

    @Override
    public <V> Function<OrderItem, V> andThen(Function<? super OrderItemDTO, ? extends V> after) {
        return Function.super.andThen(after);
    }
}
