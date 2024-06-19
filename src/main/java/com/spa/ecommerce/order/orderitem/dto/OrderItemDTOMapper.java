package com.spa.ecommerce.order.orderitem.dto;


import com.spa.ecommerce.order.orderitem.OrderItem;
import com.spa.ecommerce.product.dto.ProductDTOMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class OrderItemDTOMapper implements Function<OrderItem, OrderItemDTO> {

    @Autowired
    private ProductDTOMapper productDTOMapper;

    @Override
    public OrderItemDTO apply(OrderItem item) {
        OrderItemDTO dto = new OrderItemDTO();
        BeanUtils.copyProperties(item, dto);
        dto.setProduct(productDTOMapper.apply(item.getProduct()));
        return dto;
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
