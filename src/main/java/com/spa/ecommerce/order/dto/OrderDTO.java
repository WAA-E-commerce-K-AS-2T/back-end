package com.spa.ecommerce.order.dto;

import com.spa.ecommerce.order.PaymentMethod;
import com.spa.ecommerce.order.Status;
import lombok.Data;

@Data
public class OrderDTO {
    private Long orderId;
    private Status status;
    private double amount;

    public OrderDTO(Long orderId, Status status) {
        this.orderId = orderId;
        this.status = Status.valueOf(String.valueOf(status));
        this.amount = amount;
    }
}
