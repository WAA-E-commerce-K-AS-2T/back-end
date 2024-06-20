package com.spa.ecommerce.shoppingcart.dto;

import com.spa.ecommerce.shoppingcart.CartItem.dto.CartItemDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ShoppingCartDTO {
    private long id;
    private List<CartItemDTO> items;
    private double totalPrice;
    private int totalItems;

}
