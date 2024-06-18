package com.spa.ecommerce.shoppingcart;


import com.spa.ecommerce.shoppingcart.dto.ShoppingCartDTO;

import java.util.List;

public interface ShoppingCartService {
    public List<ShoppingCartDTO> findAll();
    public ShoppingCartDTO save(ShoppingCart shoppingCart);
    public ShoppingCartDTO findById(int id);
    public void update(int orderId, ShoppingCart shoppingCart);
    public void delete (int orderId);
}
