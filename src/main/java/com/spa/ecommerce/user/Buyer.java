package com.spa.ecommerce.user;


import com.spa.ecommerce.order.Order;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;


@Entity
@Data
public class Buyer extends User{

    @OneToMany(mappedBy = "buyer", cascade = CascadeType.ALL)
    List<Order> orderHistory = new ArrayList<Order>();


}
