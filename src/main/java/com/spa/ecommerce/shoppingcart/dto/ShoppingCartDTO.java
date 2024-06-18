package com.spa.ecommerce.shoppingcart.dto;

import com.spa.ecommerce.shoppingCartItem.dto.CartItemDTO;

import java.util.List;

public class ShoppingCartDTO {
    private int id;
    private int quantity;
    private List<CartItemDTO> items;

    public ShoppingCartDTO(int id, int quantity, List<CartItemDTO> items) {
        this.id = id;
        this.quantity = quantity;
        this.items=items;
    }
}
