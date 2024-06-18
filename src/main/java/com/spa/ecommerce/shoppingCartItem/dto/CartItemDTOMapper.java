package com.spa.ecommerce.shoppingCartItem.dto;


import com.spa.ecommerce.product.dto.ProductDTOMapper;

import com.spa.ecommerce.shoppingCartItem.CartItem;
import com.spa.ecommerce.shoppingCartItem.dto.CartItemDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class CartItemDTOMapper implements Function<CartItem, CartItemDTO> {

    @Autowired
    private ProductDTOMapper productMapper;

    @Override
    public CartItemDTO apply(CartItem cartItem) {
        return new CartItemDTO(
                cartItem.getId(),
                cartItem.getQuantity(),
                productMapper.apply(cartItem.getProduct())
        );
    }

    @Override
    public <V> Function<V, CartItemDTO> compose(Function<? super V, ? extends CartItem> before) {
        return Function.super.compose(before);
    }

    @Override
    public <V> Function<CartItem, V> andThen(Function<? super CartItemDTO, ? extends V> after) {
        return Function.super.andThen(after);
    }
}
