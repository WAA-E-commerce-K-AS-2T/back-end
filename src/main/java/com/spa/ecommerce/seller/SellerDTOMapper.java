package com.spa.ecommerce.seller;

import com.spa.ecommerce.role.RoleDTO;
import com.spa.ecommerce.role.RoleDTOMapper;
import com.spa.ecommerce.user.Seller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.function.Function;

@Service
public class SellerDTOMapper implements Function<Seller, SellerDTO> {

    @Autowired
    private RoleDTOMapper roleDTOMapper;

    @Override
    public SellerDTO apply(Seller seller) {
        Collection<RoleDTO> roles = seller.getRoles().stream().map(roleDTOMapper).toList();
        return new SellerDTO(seller.getId(), seller.getEmail(), seller.getUsername(),
                seller.getFullName(),seller.isEnabled(), roles);
    }
}
