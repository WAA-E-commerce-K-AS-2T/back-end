package com.spa.ecommerce.order;

import com.spa.ecommerce.common.Constant;
import com.spa.ecommerce.order.dto.OrderDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(Constant.ORDER_URL_PREFIX)
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping
    public ResponseEntity<OrderDTO> createOrder(Principal principal, @RequestBody OrderDTO orderDTO) {
        OrderDTO order = orderService.placeOrder(principal, orderDTO);
        return new ResponseEntity<>(order, HttpStatus.CREATED);
    }

    //get order history
    @GetMapping
    public ResponseEntity<List<OrderDTO>> getAllOrders(Principal principal) {
        List<OrderDTO> orders = orderService.findAll(principal);
        return new ResponseEntity<>(orders, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderDTO> getOrderById(@PathVariable int id) {
        OrderDTO order = orderService.findById(id);
        if (order != null) {
            return new ResponseEntity<>(order, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


//    @PutMapping("/{id}")
//    public ResponseEntity<Optional<OrderDTO>> updateOrder(@PathVariable long id, @RequestBody OrderDTO orderDTO) {
//        Optional<OrderDTO> orderDto = orderService.update(id, orderDTO);
//        return new ResponseEntity<>(orderDto, HttpStatus.OK);
//    }


}
