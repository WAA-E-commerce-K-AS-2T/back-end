package com.spa.ecommerce.order.dto;

import com.spa.ecommerce.order.Status;

public class OrderDTO {
    private int orderId;
    private Status status;
    private double amount;

    public OrderDTO(int orderId, Status status, double amount) {
        this.orderId = orderId;
        this.status = Status.valueOf(String.valueOf(status));
        this.amount = amount;
    }
}
