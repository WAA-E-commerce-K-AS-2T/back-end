package com.spa.ecommerce.profile;

import com.spa.ecommerce.address.AddressDTO;
import com.spa.ecommerce.role.RoleDTO;
import lombok.Data;

import java.util.Collection;

@Data
public class ProfileDTO {

    private String fullname;
    private Collection<RoleDTO> roles;
    private AddressDTO address;
}
