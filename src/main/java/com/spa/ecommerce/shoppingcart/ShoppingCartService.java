package com.spa.ecommerce.shoppingcart;


import com.spa.ecommerce.shoppingcart.CartItem.dto.CartItemDTO;
import com.spa.ecommerce.shoppingcart.dto.ShoppingCartDTO;

import java.security.Principal;
import java.util.List;

public interface ShoppingCartService {


    ShoppingCartDTO addItemToCart(Principal principal, CartItemDTO cartItemDTO);
    ShoppingCartDTO removeItemFromCart(Principal principal, Long productId);
    ShoppingCartDTO removeWholeCartItem(Principal principal, Long cartItemId);
    ShoppingCartDTO clearCart(Principal principal);
}
