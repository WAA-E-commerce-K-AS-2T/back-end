package com.spa.ecommerce.user;

import com.spa.ecommerce.buyer.BuyerDTO;
import com.spa.ecommerce.seller.SellerDTO;

import java.util.Optional;

public class UserWrapper {
    private SellerDTO seller;
    private BuyerDTO buyer;

    public UserWrapper(SellerDTO seller) {
        this.seller = seller;
    }

    public UserWrapper(BuyerDTO buyer) {
        this.buyer = buyer;
    }

    public Optional<SellerDTO> getSeller() {
        return Optional.ofNullable(seller);
    }

    public Optional<BuyerDTO> getBuyer() {
        return Optional.ofNullable(buyer);
    }
}
