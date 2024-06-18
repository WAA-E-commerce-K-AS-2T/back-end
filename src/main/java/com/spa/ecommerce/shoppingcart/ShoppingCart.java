package com.spa.ecommerce.shoppingcart;

import com.spa.ecommerce.product.entity.Product;
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
    private List<Product> products;
    private int quantity;
}
