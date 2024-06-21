package com.spa.ecommerce.seller;

import com.spa.ecommerce.common.Constant;
import com.spa.ecommerce.order.dto.OrderDTO;
import com.spa.ecommerce.order.orderitem.dto.OrderItemDTO;
import com.spa.ecommerce.product.dto.ProductDTO;
import com.spa.ecommerce.product.dto.ProductResponseDto;
import com.spa.ecommerce.product.service.ProductService;
import com.spa.ecommerce.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(Constant.SELLER_URL_PREFIX)
@RequiredArgsConstructor
public class SellerController {

    private final UserService userService;
    private final ProductService productService;

//    @GetMapping("/orderItems")
//    public ResponseEntity<Optional<List<OrderItemDTO>>> getOrderItems(Principal principal) {
//        Optional<List<OrderDTO>> items =  userService.getOrderItemsForSeller(principal);
//        if(items.isPresent()) {
//            return new ResponseEntity<>(items, HttpStatus.OK);
//        }else{
//            return new ResponseEntity<>(Optional.empty(), HttpStatus.NOT_FOUND);
//        }
//    }

    @GetMapping("/orders")
    public ResponseEntity<Optional<List<OrderDTO>>> getOrders(Principal principal) {
        Optional<List<OrderDTO>> orders = userService.getOrdersForSeller(principal);
        if (orders.isPresent()) {
            return new ResponseEntity<>(orders, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(Optional.empty(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/products")
    public ResponseEntity<List<ProductResponseDto>> getProducts(Principal principal) {
        List<ProductResponseDto> dto = productService.getProductsBySellerId(principal);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }




}
