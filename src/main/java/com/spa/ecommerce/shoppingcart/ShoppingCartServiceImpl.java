package com.spa.ecommerce.shoppingcart;

import com.spa.ecommerce.product.entity.Product;
import com.spa.ecommerce.product.repository.ProductRepository;
import com.spa.ecommerce.shoppingCartItem.CartItem;
import com.spa.ecommerce.shoppingCartItem.CartItemRepository;
import com.spa.ecommerce.shoppingCartItem.dto.CartItemDTO;
import com.spa.ecommerce.shoppingCartItem.dto.CartItemDTOMapper;
import com.spa.ecommerce.shoppingcart.dto.ShoppingCartDTO;
import com.spa.ecommerce.shoppingcart.dto.ShoppingCartDTOMapper;
import com.spa.ecommerce.user.User;
import com.spa.ecommerce.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {

    @Autowired
    private ShoppingCartRepository shoppingCartRepository;

    @Autowired
    private CartItemRepository cartItemRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CartItemDTOMapper cartItemDTOMapper;

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



//
//    public ShoppingCartDTO getCartByUserId(Long userId) {
//        ShoppingCart cart = shoppingCartRepository.findByBuyerId(userId)
//                .orElseThrow(() -> new RuntimeException("Shopping cart not found for user id: " + userId));
//        return shoppingCartDTOMapper.apply(cart);
//    }

    public CartItemDTO addCartItem(Principal principal, CartItemDTO cartItemDTO) {
        // Retrieve the user from the Principal
        Optional<User> userOptional = userRepository.findByEmail(principal.getName());
        if (!userOptional.isPresent()) {
            throw new RuntimeException("User not found");
        }

        User user = userOptional.get();
        Long userId = user.getId();

        // Retrieve the shopping cart for the user
        ShoppingCart cart = shoppingCartRepository.findByBuyerId(userId)
                .orElseThrow(() -> new RuntimeException("Shopping cart not found for user id: " + userId));

        // Retrieve the product from the database
        Product product = productRepository.findById(cartItemDTO.getProductDTO().getId())
                .orElseThrow(() -> new RuntimeException("Product not found with id: " + cartItemDTO.getProductDTO().getId()));

        // Create a new CartItem
        CartItem cartItem = new CartItem();
        cartItem.setQuantity(cartItemDTO.getQuantity());
        cartItem.setProduct(product);
        cartItem.setCart(cart);

        // Save the CartItem to the database
        cartItem = cartItemRepository.save(cartItem);

        // Add the CartItem to the shopping cart
        cart.getItems().add(cartItem);
        shoppingCartRepository.save(cart);

        // Return the saved CartItem as a DTO
        return cartItemDTOMapper.apply(cartItem);
    }

    public CartItemDTO updateCartItem(Principal principal, int cartItemId, CartItemDTO cartItemDTO) {
        Optional<User> userOptional = userRepository.findByEmail(principal.getName());
        if (!userOptional.isPresent()) {
            throw new RuntimeException("User not found");
        }

        User user = userOptional.get();
        Long userId = user.getId();

        ShoppingCart cart = shoppingCartRepository.findByBuyerId(userId)
                .orElseThrow(() -> new RuntimeException("Shopping cart not found for user id: " + userId));
        CartItem cartItem = cartItemRepository.findById(cartItemId)
                .orElseThrow(() -> new RuntimeException("CartItem not found with id: " + cartItemId));

        // Retrieve the product from the database
        Product product = productRepository.findById(cartItemDTO.getProductDTO().getId())
                .orElseThrow(() -> new RuntimeException("Product not found with id: " + cartItemDTO.getProductDTO().getId()));


        cartItem.setQuantity(cartItemDTO.getQuantity());
        cartItem.setProduct(product);

        cartItem = cartItemRepository.save(cartItem);
        return cartItemDTOMapper.apply(cartItem);
    }

    public void removeCartItem(Principal principal, int cartItemId) {
        Optional<User> userOptional = userRepository.findByEmail(principal.getName());
        if (!userOptional.isPresent()) {
            throw new RuntimeException("User not found");
        }

        User user = userOptional.get();
        Long userId = user.getId();

        ShoppingCart cart = shoppingCartRepository.findByBuyerId(userId)
                .orElseThrow(() -> new RuntimeException("Shopping cart not found for user id: " + userId));
        CartItem cartItem = cartItemRepository.findById(cartItemId)
                .orElseThrow(() -> new RuntimeException("CartItem not found with id: " + cartItemId));

        cart.getItems().remove(cartItem);
        cartItemRepository.delete(cartItem);
        shoppingCartRepository.save(cart);
    }

    public ShoppingCartDTO clearCart(Principal principal) {
        Optional<User> userOptional = userRepository.findByEmail(principal.getName());
        if (!userOptional.isPresent()) {
            throw new RuntimeException("User not found");
        }

        User user = userOptional.get();
        Long userId = user.getId();

        ShoppingCart cart = shoppingCartRepository.findByBuyerId(userId)
                .orElseThrow(() -> new RuntimeException("Shopping cart not found for user id: " + userId));

        cart.getItems().clear();
        shoppingCartRepository.save(cart);

        return shoppingCartDTOMapper.apply(cart);
    }



}
