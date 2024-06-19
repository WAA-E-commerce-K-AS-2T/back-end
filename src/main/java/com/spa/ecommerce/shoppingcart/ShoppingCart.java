package com.spa.ecommerce.shoppingcart;

import com.spa.ecommerce.common.Auditable;
import com.spa.ecommerce.product.entity.Product;
import com.spa.ecommerce.user.Buyer;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class ShoppingCart extends Auditable<Buyer> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer quantity;

    @ManyToOne
    private Product product;
}
