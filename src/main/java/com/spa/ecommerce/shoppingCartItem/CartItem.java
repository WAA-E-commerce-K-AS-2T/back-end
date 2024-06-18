package com.spa.ecommerce.shoppingCartItem;

import com.spa.ecommerce.product.entity.Product;
import com.spa.ecommerce.shoppingcart.ShoppingCart;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class CartItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int quantity;
    @ManyToOne
    private ShoppingCart cart;
    @OneToOne
    private Product product;
    //discount
}
