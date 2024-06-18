package com.spa.ecommerce.shoppingcart.dto;

public class ShoppingCartDTO {
    private int id;
    private int quantity;

    public ShoppingCartDTO(int id, int quantity) {
        this.id = id;
        this.quantity = quantity;
    }
}
