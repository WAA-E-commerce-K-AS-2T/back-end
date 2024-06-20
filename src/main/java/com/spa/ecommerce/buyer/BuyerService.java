package com.spa.ecommerce.buyer;

import com.spa.ecommerce.address.AddressDTO;

import java.security.Principal;

public interface BuyerService {
    AddressDTO getAddressByBuyer(Principal principal);
}
