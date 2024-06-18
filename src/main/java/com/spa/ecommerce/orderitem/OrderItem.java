package com.spa.ecommerce.orderitem;

import com.spa.ecommerce.order.Order;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private int itemId;
    @OneToMany
    private List<Product> products;
    private int quantity;
    @ManyToOne
    private Order order;
}
