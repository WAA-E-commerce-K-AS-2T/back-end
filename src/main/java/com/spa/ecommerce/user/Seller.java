package com.spa.ecommerce.user;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.Data;

import java.util.Collection;

@Entity
@Data
public class Seller extends User{
//    @OneToMany(mappedBy = "owner")
//    private Collection<Products> properties;
}
