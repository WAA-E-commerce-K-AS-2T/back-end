package com.spa.ecommerce.order.dto;

import com.spa.ecommerce.order.PaymentMethod;
import com.spa.ecommerce.order.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class OrderDTO {
    private int orderId;
    private Status status;
    private double amount;
    private PaymentMethod paymentMethod;
}
