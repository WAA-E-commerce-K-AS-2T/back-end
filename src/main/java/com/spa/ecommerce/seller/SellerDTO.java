package com.spa.ecommerce.seller;

import com.spa.ecommerce.role.RoleDTO;
import com.spa.ecommerce.user.UserDTO;

import java.util.Collection;

public class SellerDTO extends UserDTO {
    public SellerDTO(Long id, String email, String username, String fullName, Boolean isActive, Collection<RoleDTO> roles) {
        super(id, email, username, fullName, isActive, roles);
    }
}
