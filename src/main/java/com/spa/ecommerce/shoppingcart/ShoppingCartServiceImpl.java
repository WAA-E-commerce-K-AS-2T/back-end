package com.spa.ecommerce.shoppingcart;

import com.spa.ecommerce.shoppingcart.dto.ShoppingCartDTO;
import com.spa.ecommerce.shoppingcart.dto.ShoppingCartDTOMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {

    @Autowired
    private ShoppingCartRepository shoppingCartRepository;

    @Autowired
    private ShoppingCartDTOMapper shoppingCartDTOMapper;

    @Override
    public List<ShoppingCartDTO> findAll() {
        return shoppingCartRepository.findAll()
                .stream()
                .map(shoppingCartDTOMapper)
                .collect(Collectors.toList());
    }

    @Override
    public ShoppingCartDTO save(ShoppingCart shoppingCart) {
        ShoppingCart savedCart = shoppingCartRepository.save(shoppingCart);
        return shoppingCartDTOMapper.apply(savedCart);
    }

    @Override
    public ShoppingCartDTO findById(int id) {
        return shoppingCartRepository.findById( id)
                .map(shoppingCartDTOMapper)
                .orElse(null);
    }

    @Override
    public void update(int cartId, ShoppingCart shoppingCart) {
        if (shoppingCartRepository.existsById( cartId)) {
            shoppingCart.setCartId( cartId);
            shoppingCartRepository.save(shoppingCart);
        }
    }

    @Override
    public void delete(int cartId) {
        shoppingCartRepository.deleteById( cartId);
    }
}
