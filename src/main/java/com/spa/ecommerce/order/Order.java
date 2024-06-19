package com.spa.ecommerce.order;

import com.spa.ecommerce.orderitem.OrderItem;
import com.spa.ecommerce.user.User;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Entity
@Data
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int orderId;
    @ManyToOne
    private User buyer;
    private Status  status;
    private double amount;
    private LocalDate date;
    @OneToMany(mappedBy = "order")
    private List<OrderItem> orderItems;


//    @ManyToOne
//    private Address shippingAddress;
//
//    @ManyToOne
//    private Address billingAddress;

    public Order(){}

    public Order(User buyer, Status status, double amount, LocalDate date, List<OrderItem> orderItems) {
        this.buyer = buyer;
        this.status = status;
        this.amount = amount;
        this.date = date;
        this.orderItems = orderItems;
    }
}
