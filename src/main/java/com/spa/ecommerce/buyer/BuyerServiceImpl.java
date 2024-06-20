package com.spa.ecommerce.buyer;

import com.spa.ecommerce.address.Address;
import com.spa.ecommerce.address.AddressDTO;
import com.spa.ecommerce.address.AddressDTOMapper;
import com.spa.ecommerce.exception.ProductException;
import com.spa.ecommerce.exception.UnAuthorizedException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BuyerServiceImpl implements BuyerService{

    private final BuyerRepository buyerRepository;
    private final AddressDTOMapper addressDTOMapper;

    @Override
    public AddressDTO getAddressByBuyer(Principal principal) {
        String email = principal.getName();
        if(email == null){
            throw new UnAuthorizedException("UnAuthorized User");
        }
        Optional<Address> addressOptional =  buyerRepository.findAddressByEmail(email);
        if(addressOptional.isPresent()){
            return addressDTOMapper.apply(addressOptional.get());
        }else{
            throw new ProductException("Address Not Found");
        }
    }
}
