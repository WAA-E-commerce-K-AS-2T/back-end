package com.spa.ecommerce.order.dto;

import com.spa.ecommerce.order.PaymentMethod;
import com.spa.ecommerce.order.Status;
import com.spa.ecommerce.order.orderitem.dto.OrderItemDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class OrderDTO {
    private long id;
    private Status status;
    private double amount;
    private PaymentMethod paymentMethod;
    private List<OrderItemDTO> orderItems;
}
