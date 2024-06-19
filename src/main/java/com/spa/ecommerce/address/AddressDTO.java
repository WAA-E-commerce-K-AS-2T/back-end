package com.spa.ecommerce.address;

import lombok.Data;

@Data
public class AddressDTO {
    private Long id;
    private String address1;
    private String address2;
    private String address3;
    private String address4;
    private String city;
    private String state;
    private String zipcode;
    private String pincode;

    public AddressDTO(Long id, String address1, String address2, String address3,
                      String address4, String city, String state,
                      String zipcode, String pincode) {
        this.id=id;
        this.address1=address1;
        this.address2=address2;
        this.address3=address3;
        this.address4=address4;
        this.city=city;
        this.state=state;
        this.zipcode=zipcode;
        this.pincode=pincode;
    }

    public AddressDTO() {

    }
}
