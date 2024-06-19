package com.spa.ecommerce.shoppingCartItem.dto;

import com.spa.ecommerce.product.dto.ProductDTO;
import com.spa.ecommerce.product.entity.Product;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
public class CartItemDTO {
    private int id;
    private int quantity;
    //discount
    private ProductDTO productDTO;

    public CartItemDTO(int id, int quantity, ProductDTO productDTO) {
        this.id = id;
        this.quantity = quantity;
        this.productDTO = productDTO;
    }
}
