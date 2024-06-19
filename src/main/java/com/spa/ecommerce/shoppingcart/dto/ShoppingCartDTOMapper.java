package com.spa.ecommerce.shoppingcart.dto;

import com.spa.ecommerce.shoppingCartItem.dto.CartItemDTO;

import com.spa.ecommerce.shoppingCartItem.dto.CartItemDTOMapper;
import com.spa.ecommerce.shoppingcart.ShoppingCart;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public class ShoppingCartDTOMapper implements Function<ShoppingCart, ShoppingCartDTO> {

    @Autowired
    private CartItemDTOMapper cartItemDTOMapper;

    @Override
    public ShoppingCartDTO apply(ShoppingCart shoppingCart) {
        List<CartItemDTO> items = shoppingCart.getItems().stream()
                .map(cartItemDTOMapper)
                .collect(Collectors.toList());
        int totalQuantity = items.stream().mapToInt(CartItemDTO::getQuantity).sum();

        return new ShoppingCartDTO(
                shoppingCart.getCartId(),
                totalQuantity,
                items
        );
    }

    @Override
    public <V> Function<V, ShoppingCartDTO> compose(Function<? super V, ? extends ShoppingCart> before) {
        return Function.super.compose(before);
    }

    @Override
    public <V> Function<ShoppingCart, V> andThen(Function<? super ShoppingCartDTO, ? extends V> after) {
        return Function.super.andThen(after);
    }
}
