package com.spa.ecommerce.shoppingcart;

import com.spa.ecommerce.product.dto.ProductDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ShoppingCartDTO {
    private Long id;
    private Integer quantity;
    private Float totalPrice;
    private ProductDTO product;

    public ShoppingCartDTO(Long id, Integer quantity, ProductDTO product) {
        this.id = id;
        this.quantity = quantity;
        this.product = product;
        this.totalPrice = (float) (quantity * product.getPrice());
    }
}
