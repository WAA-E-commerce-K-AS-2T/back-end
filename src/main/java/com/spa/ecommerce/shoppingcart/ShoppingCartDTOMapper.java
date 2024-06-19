package com.spa.ecommerce.shoppingcart;


import com.spa.ecommerce.product.dto.ProductDTOMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.function.Function;

@Service
public class ShoppingCartDTOMapper implements Function<ShoppingCart, ShoppingCartDTO> {

    @Autowired
    private ProductDTOMapper productDTOMapper;

    @Override
    public ShoppingCartDTO apply(ShoppingCart shoppingCart) {
        return new ShoppingCartDTO(
                shoppingCart.getId(),
                shoppingCart.getQuantity(),
                productDTOMapper.apply(shoppingCart.getProduct())
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
