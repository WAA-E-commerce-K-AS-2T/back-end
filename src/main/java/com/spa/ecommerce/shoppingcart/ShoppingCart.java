package com.spa.ecommerce.shoppingcart;

import com.spa.ecommerce.product.entity.Product;
import com.spa.ecommerce.shoppingcart.CartItem.entity.CartItem;
import com.spa.ecommerce.user.User;
import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class ShoppingCart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cart")
    private List<CartItem> cartItems = new ArrayList<>();

    private double totalPrice;

    @OneToOne
    @JoinColumn(name = "buyer_id")
    private User buyer;
}
