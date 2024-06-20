package com.spa.ecommerce.address;

import com.spa.ecommerce.user.Buyer;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;

@Data
@Entity
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    private String address1;
    private String address2;
    private String address3;
    private String address4;
    private String city;
    private String state;
    private String zipcode;
    private String pincode;

}
