package com.spa.ecommerce.orderitem.dto;

import lombok.Data;

@Data
public class OrderItemDTO {
    private int itemId;
    private int quantity;

    public OrderItemDTO(int itemId, int quantity) {
        this.itemId = itemId;
        this.quantity = quantity;
    }
}

