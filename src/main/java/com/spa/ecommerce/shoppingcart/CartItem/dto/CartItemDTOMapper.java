package com.spa.ecommerce.shoppingcart.CartItem.dto;

import com.spa.ecommerce.product.entity.Product;
import com.spa.ecommerce.product.repository.ProductRepository;
import com.spa.ecommerce.shoppingcart.CartItem.entity.CartItem;
import com.spa.ecommerce.shoppingcart.ShoppingCart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CartItemDTOMapper {

    @Autowired
    private ProductRepository productRepository;

    public CartItem toEntity(CartItemDTO dto, ShoppingCart cart) {
        CartItem cartItem = new CartItem();
        cartItem.setId(dto.getId());
        cartItem.setCart(cart);

        Product product = productRepository.findById(dto.getProductId())
                .orElseThrow(() -> new RuntimeException("Product not found"));
        cartItem.setProduct(product);

        cartItem.setQuantity(dto.getQuantity());
        cartItem.setPrice(dto.getPrice());
        return cartItem;
    }

    public CartItemDTO toDTO(CartItem entity) {
        CartItemDTO dto = new CartItemDTO();
        dto.setId(entity.getId());
        dto.setProductId(entity.getProduct().getId());
        dto.setQuantity(entity.getQuantity());
        dto.setPrice(entity.getPrice());
        return dto;
    }
}
