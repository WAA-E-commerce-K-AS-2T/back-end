package com.spa.ecommerce.buyer;

import com.spa.ecommerce.address.AddressDTO;
import com.spa.ecommerce.address.AddressDTOMapper;
import com.spa.ecommerce.role.RoleDTO;
import com.spa.ecommerce.role.RoleDTOMapper;
import com.spa.ecommerce.seller.SellerDTO;
import com.spa.ecommerce.user.Buyer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.function.Function;

@Service
public class BuyerDTOMapper implements Function<Buyer, BuyerDTO> {
    @Autowired
    private RoleDTOMapper roleDTOMapper;
    @Autowired
    private AddressDTOMapper addressDTOMapper;
    @Override
    public BuyerDTO apply(Buyer buyer) {
        Collection<RoleDTO> roles = buyer.getRoles().stream().map(roleDTOMapper).toList();
        AddressDTO addressDTO = new AddressDTO();
        if(buyer.getAddress() != null) {
            addressDTO = addressDTOMapper.apply(buyer.getAddress());
        }
        return new BuyerDTO(buyer.getId(), buyer.getEmail(), buyer.getUsername(),
                buyer.getFullName(),buyer.isEnabled(), roles, addressDTO
                );
    }
}
