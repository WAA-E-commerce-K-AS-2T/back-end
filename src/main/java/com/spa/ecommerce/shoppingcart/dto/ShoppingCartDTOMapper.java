package com.spa.ecommerce.shoppingcart.dto;


import com.spa.ecommerce.shoppingcart.ShoppingCart;
import org.springframework.stereotype.Service;
import java.util.function.Function;


@Service
public class ShoppingCartDTOMapper implements Function<ShoppingCart, ShoppingCartDTO> {


    @Override
    public ShoppingCartDTO apply(ShoppingCart shoppingCart) {
        return new ShoppingCartDTO(
                shoppingCart.getCartId(),
                shoppingCart.getQuantity()
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
