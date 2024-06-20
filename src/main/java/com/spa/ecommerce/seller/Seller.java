package com.spa.ecommerce.seller;

import com.spa.ecommerce.order.orderitem.OrderItem;
import com.spa.ecommerce.product.entity.Product;
import com.spa.ecommerce.user.User;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class Seller extends User {
    @OneToMany(mappedBy = "seller", fetch = FetchType.LAZY)
    private List<Product> products;

    @OneToMany(cascade= CascadeType.ALL)
    private List<OrderItem> orderItems = new ArrayList<OrderItem>();
}
