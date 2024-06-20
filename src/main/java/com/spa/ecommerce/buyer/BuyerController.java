package com.spa.ecommerce.buyer;

import com.spa.ecommerce.address.AddressDTO;
import com.spa.ecommerce.common.Constant;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping(Constant.BUYER_URL_PREFIX)
@RequiredArgsConstructor
public class BuyerController {
    private final BuyerService buyerService;

    @GetMapping("/address")
        public ResponseEntity<AddressDTO> getUserAddress(Principal principal){
           return new ResponseEntity<>(buyerService.getAddressByBuyer(principal), HttpStatus.OK);
        }
}
