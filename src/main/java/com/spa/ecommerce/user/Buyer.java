package com.spa.ecommerce.user;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.Data;

import java.util.Collection;

@Entity
@Data
public class Buyer extends User{


//    @OneToMany(mappedBy = "customer")
//    private Collection<Favorite> favorites;

}
