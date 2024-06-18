package com.spa.ecommerce.user;

import com.spa.ecommerce.product.entity.Product;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class Seller extends User{
    @OneToMany(mappedBy = "seller", fetch = FetchType.LAZY)
    private List<Product> products;
}
