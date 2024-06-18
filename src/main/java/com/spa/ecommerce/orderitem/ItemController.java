package com.spa.ecommerce.orderitem;

import com.spa.ecommerce.orderitem.dto.OrderItemDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/items")
public class ItemController {

    @Autowired
    private ItemService itemService;

    @PostMapping
    public ResponseEntity<OrderItemDTO> createItem(@RequestBody OrderItem orderItem) {
        OrderItemDTO savedOrderItem = itemService.save(orderItem);
        return new ResponseEntity<>(savedOrderItem, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderItemDTO> getItemById(@PathVariable int id) {
        OrderItemDTO orderItem = itemService.findById(id);
        if (orderItem != null) {
            return new ResponseEntity<>(orderItem, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}")
    public void updateItem(@PathVariable int id, @RequestBody OrderItem orderItem) {
         itemService.update(id, orderItem);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteItem(@PathVariable int id) {
        itemService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping
    public ResponseEntity<List<OrderItemDTO>> getAllItems() {
        List<OrderItemDTO> orderItems = itemService.findAll();
        return new ResponseEntity<>(orderItems, HttpStatus.OK);
    }

}