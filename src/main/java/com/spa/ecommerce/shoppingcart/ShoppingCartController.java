package com.spa.ecommerce.shoppingcart;

import com.spa.ecommerce.shoppingcart.dto.ShoppingCartDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/cart")
public class ShoppingCartController {

    @Autowired
    private ShoppingCartService shoppingCartService;

    @GetMapping
    public ResponseEntity<List<ShoppingCartDTO>> getAllCarts() {
        List<ShoppingCartDTO> carts = shoppingCartService.findAll();
        return new ResponseEntity<>(carts, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ShoppingCartDTO> createCart(@RequestBody ShoppingCart shoppingCart) {
        ShoppingCartDTO savedCart = shoppingCartService.save(shoppingCart);
        return new ResponseEntity<>(savedCart, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ShoppingCartDTO> getCartById(@PathVariable int id) {
        ShoppingCartDTO cart = shoppingCartService.findById(id);
        if (cart != null) {
            return new ResponseEntity<>(cart, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateCart(@PathVariable int id, @RequestBody ShoppingCart shoppingCart) {
        shoppingCartService.update(id, shoppingCart);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCart(@PathVariable int id) {
        shoppingCartService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
