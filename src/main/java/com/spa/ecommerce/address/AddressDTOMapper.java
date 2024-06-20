package com.spa.ecommerce.address;


import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class AddressDTOMapper implements Function<Address, AddressDTO> {
    @Override
    public AddressDTO apply(Address address) {
        return new AddressDTO(address.getId(),
                address.getAddress1(),
                address.getAddress2(),
                address.getAddress3(),
                address.getAddress4(),
                address.getCity(),
                address.getState(),
                address.getZipcode(),
                address.getPincode());
    }

    @Override
    public <V> Function<V, AddressDTO> compose(Function<? super V, ? extends Address> before) {
        return Function.super.compose(before);
    }

    @Override
    public <V> Function<Address, V> andThen(Function<? super AddressDTO, ? extends V> after) {
        return Function.super.andThen(after);
    }
}
