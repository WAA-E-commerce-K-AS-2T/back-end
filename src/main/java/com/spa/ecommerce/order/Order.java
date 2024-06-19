package com.spa.ecommerce.order;

import com.spa.ecommerce.common.Auditable;
import com.spa.ecommerce.orderitem.OrderItem;
import com.spa.ecommerce.shoppingcart.ShoppingCart;
import com.spa.ecommerce.user.User;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.Fetch;

import java.time.LocalDate;
import java.util.List;

@Entity
@Data
@Table(name = "orders")
public class Order extends Auditable<User> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Status  status;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, targetEntity = ShoppingCart.class)
    @JoinColumn(name = "order_id", referencedColumnName = "id")
    private List<ShoppingCart> cartItems;

//    @ManyToOne
//    private Address shippingAddress;
//
//    @ManyToOne
//    private Address billingAddress;

}
