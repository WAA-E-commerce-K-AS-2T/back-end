package com.spa.ecommerce.buyer;

import com.spa.ecommerce.address.AddressDTO;
import com.spa.ecommerce.role.RoleDTO;
import com.spa.ecommerce.user.UserDTO;

import java.util.Collection;

public class BuyerDTO extends UserDTO {

    private AddressDTO address;

    public BuyerDTO(Long id, String email, String username, String fullName, Boolean isActive, Collection<RoleDTO> roles,AddressDTO address) {
        super(id, email, username, fullName, isActive, roles);
        this.address = address;
    }

    public AddressDTO getAddress() {
        return address;
    }

    public void setAddress(AddressDTO address) {
        this.address = address;
    }
}
