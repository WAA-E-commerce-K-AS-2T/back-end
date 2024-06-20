package com.spa.ecommerce.shoppingcart.dto;


import com.spa.ecommerce.shoppingcart.CartItem.dto.CartItemDTOMapper;
import com.spa.ecommerce.shoppingcart.ShoppingCart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.function.Function;
import java.util.stream.Collectors;


@Service
public class ShoppingCartDTOMapper implements Function<ShoppingCart, ShoppingCartDTO> {

    @Autowired
    private CartItemDTOMapper cartItemDTOMapper;


    @Override
    public ShoppingCartDTO apply(ShoppingCart shoppingCart) {
        return new ShoppingCartDTO(
                shoppingCart.getId(),
                shoppingCart.getCartItems().stream().map(dto -> cartItemDTOMapper.toDTO(dto)).collect(Collectors.toList()),
                shoppingCart.getTotalPrice(),
                shoppingCart.getCartItems().size()
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
