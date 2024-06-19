package com.spa.ecommerce.shoppingcart.CartItem.entity;

import com.spa.ecommerce.product.entity.Product;
import com.spa.ecommerce.shoppingcart.ShoppingCart;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CartItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "cart_id", nullable = false)
    private ShoppingCart cart;

    @OneToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    private double price;

    private int quantity;
}
