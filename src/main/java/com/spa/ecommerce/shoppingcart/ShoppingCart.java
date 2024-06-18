package com.spa.ecommerce.shoppingcart;

import com.spa.ecommerce.product.entity.Product;
import com.spa.ecommerce.shoppingCartItem.CartItem;
import com.spa.ecommerce.user.User;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class ShoppingCart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int cartId;
    @OneToMany
    private List<CartItem> items;
    @OneToOne
    private User buyer;
}
