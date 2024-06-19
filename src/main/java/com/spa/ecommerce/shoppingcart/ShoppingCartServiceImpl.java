package com.spa.ecommerce.shoppingcart;

import com.spa.ecommerce.product.entity.Product;
import com.spa.ecommerce.product.repository.ProductRepository;
import com.spa.ecommerce.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {

    @Autowired
    private ShoppingCartRepository shoppingCartRepository;
    @Autowired
    private ShoppingCartDTOMapper shoppingCartDTOMapper;
    @Autowired
    private ProductRepository productRepository;

    @Override
    public Collection<ShoppingCartDTO> getAll() {
        return shoppingCartRepository.findAll().stream().map(shoppingCartDTOMapper).collect(Collectors.toList());
    }

    @Override
    public Optional<ShoppingCartDTO> get(Long id) {
        return shoppingCartRepository.findById(id).map(shoppingCartDTOMapper);
    }

    @Override
    public Optional<ShoppingCartDTO> save(ShoppingCartDTO entity) {
        Optional<Product> product= productRepository.findById(entity.getProduct().getId());
        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.setQuantity(entity.getQuantity());
        if(product.isPresent())
            shoppingCart.setProduct(product.get());
        shoppingCart = shoppingCartRepository.save(shoppingCart);
        return Optional.of(shoppingCartDTOMapper.apply(shoppingCart));
    }

    @Override
    public Optional<ShoppingCartDTO> update(Long id, ShoppingCartDTO entity) {
        Optional<ShoppingCart> existingUserOpt = shoppingCartRepository.findById(id);
        Optional<Product> product= productRepository.findById(entity.getProduct().getId());
        if (existingUserOpt.isPresent() && product.isPresent()) {
            ShoppingCart existingShoppingCart = existingUserOpt.get();
            existingShoppingCart.setQuantity(entity.getQuantity());
            existingShoppingCart.setProduct(product.get());
            shoppingCartRepository.save(existingShoppingCart);

            return Optional.of(shoppingCartDTOMapper.apply(existingShoppingCart));
        } else {
            return Optional.empty();
        }
    }

    @Override
    public Optional<ShoppingCartDTO> delete(Long id, ShoppingCartDTO entity) {
        Optional<ShoppingCart> existingUserOpt = shoppingCartRepository.findById(id);
        if (existingUserOpt.isPresent()) {
            shoppingCartRepository.deleteById(id);
            return Optional.of(shoppingCartDTOMapper.apply(existingUserOpt.get()));
        } else {
            return Optional.empty();
        }
    }
}