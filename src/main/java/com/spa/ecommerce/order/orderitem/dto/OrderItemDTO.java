package com.spa.ecommerce.order.orderitem.dto;

import com.spa.ecommerce.order.dto.OrderDTO;
import com.spa.ecommerce.product.dto.ProductDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderItemDTO {
    private int itemId;
    private int quantity;
    private ProductDTO product;
}

