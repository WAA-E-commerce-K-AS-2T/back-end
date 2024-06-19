package com.spa.ecommerce.user;

import com.spa.ecommerce.shoppingcart.ShoppingCart;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.Data;

import java.util.Collection;

@Entity
@Data
public class Buyer extends User{

    @OneToOne(mappedBy = "buyer", cascade = CascadeType.ALL, orphanRemoval = true)
    private ShoppingCart cart;

}
