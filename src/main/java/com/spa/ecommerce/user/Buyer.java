package com.spa.ecommerce.user;


import com.spa.ecommerce.address.Address;
import com.spa.ecommerce.order.Order;
import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;


@Entity
@Data
public class Buyer extends User {

    @OneToMany(mappedBy = "buyer", cascade = CascadeType.ALL)
    List<Order> orderHistory = new ArrayList<Order>();

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id", referencedColumnName = "id")
    private Address address;

}
