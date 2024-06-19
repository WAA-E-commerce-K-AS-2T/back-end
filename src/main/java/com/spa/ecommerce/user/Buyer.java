package com.spa.ecommerce.user;

import com.spa.ecommerce.shoppingcart.ShoppingCart;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.Data;

import java.util.Collection;

@Entity
@Data
public class Buyer extends User{


//    @OneToMany(mappedBy = "customer")
//    private Collection<Favorite> favorites;

    @OneToOne(mappedBy = "buyer")
    private ShoppingCart shoppingCart;

}
