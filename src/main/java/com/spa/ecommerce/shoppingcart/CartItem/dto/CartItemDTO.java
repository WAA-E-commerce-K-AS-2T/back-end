package com.spa.ecommerce.shoppingcart.CartItem.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartItemDTO {
    private long id;
    private long productId;
    private int quantity;
    private double price;
}
