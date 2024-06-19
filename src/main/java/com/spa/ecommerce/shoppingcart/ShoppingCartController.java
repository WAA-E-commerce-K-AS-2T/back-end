package com.spa.ecommerce.shoppingcart;

import com.spa.ecommerce.common.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Optional;

@RestController
@RequestMapping(Constant.SHOPPINGCART_URL_PREFIX)
public class ShoppingCartController {

    @Autowired
    private ShoppingCartService shoppingCartService;

    @GetMapping()
    public ResponseEntity<Collection<ShoppingCartDTO>> index(){
        return new ResponseEntity<>(shoppingCartService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public  ResponseEntity<ShoppingCartDTO> get(@PathVariable("id") Long id) {
        return shoppingCartService.get(id).map(ResponseEntity::ok).orElseGet(()->ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @PostMapping
    public ResponseEntity<ShoppingCartDTO> save(@RequestBody ShoppingCartDTO shoppingCartDTO) {
        Optional<ShoppingCartDTO> savedUser = shoppingCartService.save(shoppingCartDTO);
        return savedUser.map(ResponseEntity::ok)
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<ShoppingCartDTO> update(@PathVariable Long id, @RequestBody ShoppingCartDTO shoppingCartDTO) {
        Optional<ShoppingCartDTO> updatedUserOpt = shoppingCartService.update(id, shoppingCartDTO);
        return updatedUserOpt
                .map(userDTO -> new ResponseEntity<>(userDTO, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ShoppingCartDTO> delete(@PathVariable Long id, @RequestBody ShoppingCartDTO shoppingCartDTO) {
        Optional<ShoppingCartDTO> updatedUserOpt = shoppingCartService.delete(id, shoppingCartDTO);
        return updatedUserOpt
                .map(userDTO -> new ResponseEntity<>(userDTO, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}
