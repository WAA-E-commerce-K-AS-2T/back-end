package com.spa.ecommerce.shoppingcart;


import com.spa.ecommerce.shoppingCartItem.dto.CartItemDTO;
import com.spa.ecommerce.shoppingcart.dto.ShoppingCartDTO;

import java.security.Principal;
import java.util.List;

public interface ShoppingCartService {
    public List<ShoppingCartDTO> findAll();
    public ShoppingCartDTO save(ShoppingCart shoppingCart);
    public ShoppingCartDTO findById(int id);
    public void update(int shoppingCartId, ShoppingCart shoppingCart);
    public void delete (int shoppingCartId);




//    ShoppingCartDTO getCartByUserId(Long userId);

    CartItemDTO updateCartItem(Principal principal,int cartItemId, CartItemDTO cartItemDTO);

    void removeCartItem(Principal principal, int cartItemId);

    ShoppingCartDTO clearCart(Principal principal);

    CartItemDTO addCartItem(Principal principal, CartItemDTO cartItemDTO);
}
