package com.spa.ecommerce.shoppingcart;

import com.spa.ecommerce.common.Constant;
import com.spa.ecommerce.shoppingcart.CartItem.dto.CartItemDTO;
import com.spa.ecommerce.shoppingcart.dto.ShoppingCartDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping(Constant.SHOPPINGCART_URL_PREFIX)
public class ShoppingCartController {

    @Autowired
    private ShoppingCartService shoppingCartService;

    @PostMapping("/cartItems")
    public ResponseEntity<ShoppingCartDTO> addItemToCart(Principal principal, @RequestBody CartItemDTO cartItemDTO) {
        ShoppingCartDTO shoppingCartDTO = shoppingCartService.addItemToCart(principal, cartItemDTO);
        return new ResponseEntity<>(shoppingCartDTO, HttpStatus.OK);
    }

    @DeleteMapping("cartItems/product/{productId}")
    public ResponseEntity<ShoppingCartDTO> removeItemFromCart(Principal principal, @PathVariable Long productId) {
        ShoppingCartDTO cart = shoppingCartService.removeItemFromCart(principal, productId);
        return new ResponseEntity<>(cart, HttpStatus.OK);
    }

    @DeleteMapping("cartItems/{cartItemId}")
    public ResponseEntity<ShoppingCartDTO> removeWholeCartItem(
            @PathVariable Long cartItemId,
            Principal principal) {
        ShoppingCartDTO cart = shoppingCartService.removeWholeCartItem(principal, cartItemId);
        return new ResponseEntity<>(cart, HttpStatus.OK);
    }

    @DeleteMapping("/clear")
    public ResponseEntity<ShoppingCartDTO> clearCart(Principal principal) {
        ShoppingCartDTO cart = shoppingCartService.clearCart(principal);
        return new ResponseEntity<>(cart, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<ShoppingCartDTO> getShoppingCart(Principal principal) {
        ShoppingCartDTO shoppingCartDTO = shoppingCartService.getShoppingCart(principal);
        return new ResponseEntity<>(shoppingCartDTO, HttpStatus.OK);
    }
}



