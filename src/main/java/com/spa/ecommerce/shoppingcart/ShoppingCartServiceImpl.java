package com.spa.ecommerce.shoppingcart;

import com.spa.ecommerce.product.entity.Product;
import com.spa.ecommerce.product.repository.ProductRepository;
import com.spa.ecommerce.shoppingcart.CartItem.dto.CartItemDTO;
import com.spa.ecommerce.shoppingcart.CartItem.entity.CartItem;
import com.spa.ecommerce.shoppingcart.CartItem.repository.CartItemRepository;
import com.spa.ecommerce.shoppingcart.dto.ShoppingCartDTO;
import com.spa.ecommerce.shoppingcart.dto.ShoppingCartDTOMapper;
import com.spa.ecommerce.user.User;
import com.spa.ecommerce.user.UserRepository;
import jakarta.transaction.Transactional;
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
    private ShoppingCartDTOMapper shoppingCartDTOMapper;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CartItemRepository cartItemRepository;

    @Transactional
    public ShoppingCartDTO addItemToCart(Principal principal, CartItemDTO cartItemDTO) {
        String email = principal.getName();

        Optional<User> userOptional = userRepository.findByEmail(email);
        if (userOptional.isPresent()) {
            // Get user cart
            User user = userOptional.get();
            Long userId = user.getId();
            ShoppingCart cart = shoppingCartRepository.findByBuyerId(userId);
            if (cart == null) {
                cart = new ShoppingCart();
                cart.setBuyer(user);
                shoppingCartRepository.save(cart);
            }

            Long productId = cartItemDTO.getProductId();
            Product product = productRepository.findById(productId).orElseThrow(() -> new RuntimeException("Product not found"));

            // Check if the product already exists in the cart
            CartItem existingCartItem = cart.getCartItems().stream()
                    .filter(item -> item.getProduct().getId().equals(productId))
                    .findFirst()
                    .orElse(null);

            if (existingCartItem != null) {
                // Update quantity and price if the item already exists
                existingCartItem.setQuantity(existingCartItem.getQuantity() + cartItemDTO.getQuantity());
                existingCartItem.setPrice(existingCartItem.getQuantity() * product.getPrice());
            } else {
                // Add new item to the cart
                CartItem cartItem = new CartItem();
                cartItem.setCart(cart);
                cartItem.setProduct(product);
                cartItem.setQuantity(cartItemDTO.getQuantity());
                cartItem.setPrice(cartItemDTO.getQuantity() * product.getPrice());
                cart.getCartItems().add(cartItem);
            }

            // Update the cart's total price
            double totalPrice = cart.getCartItems().stream().mapToDouble(CartItem::getPrice).sum();
            cart.setTotalPrice(totalPrice);

            // Save the updated cart
            ShoppingCart shoppingCart = shoppingCartRepository.save(cart);

            // Ensure the cart items are saved (though cascade should handle this)
            for (CartItem item : cart.getCartItems()) {
                cartItemRepository.save(item);
            }

            return shoppingCartDTOMapper.apply(shoppingCart);
        }

        return null;
    }

    @Transactional
    public ShoppingCartDTO removeItemFromCart(Principal principal, Long productId) {
        String email = principal.getName();

        Optional<User> userOptional = userRepository.findByEmail(email);
        if (userOptional.isPresent()) {
            // Get user cart
            User user = userOptional.get();
            Long userId = user.getId();
            ShoppingCart cart = shoppingCartRepository.findByBuyerId(userId);
            if (cart != null) {
                // Find the cart item by product ID
                CartItem cartItem = cart.getCartItems().stream()
                        .filter(item -> item.getProduct().getId().equals(productId))
                        .findFirst()
                        .orElse(null);

                if (cartItem != null) {
                    if (cartItem.getQuantity() > 1) {
                        // Reduce quantity by 1 if multiple quantities
                        cartItem.setQuantity(cartItem.getQuantity() - 1);
                        cartItem.setPrice(cartItem.getQuantity() * cartItem.getProduct().getPrice());
                        cartItemRepository.save(cartItem);
                    } else {
                        // Remove the item from the cart if quantity is 1
                        cart.getCartItems().remove(cartItem);
                        cartItemRepository.delete(cartItem);
                    }

                    // Update the cart's total price
                    double totalPrice = cart.getCartItems().stream().mapToDouble(CartItem::getPrice).sum();
                    cart.setTotalPrice(totalPrice);

                    // Save the updated cart
                    ShoppingCart shoppingCart = shoppingCartRepository.save(cart);
                    return shoppingCartDTOMapper.apply(shoppingCart);
                }
            }
        }

        return null;
    }

    @Transactional
    public ShoppingCartDTO removeWholeCartItem(Principal principal, Long cartItemId) {
        String email = principal.getName();

        Optional<User> userOptional = userRepository.findByEmail(email);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            Long userId = user.getId();
            ShoppingCart cart = shoppingCartRepository.findByBuyerId(userId);
            if (cart != null) {
                CartItem cartItem = cartItemRepository.findById(cartItemId)
                        .orElseThrow(() -> new RuntimeException("CartItem not found"));

                if (cart.getCartItems().contains(cartItem)) {
                    cart.getCartItems().remove(cartItem);
                    cartItemRepository.delete(cartItem);

                    double totalPrice = cart.getCartItems().stream().mapToDouble(CartItem::getPrice).sum();
                    cart.setTotalPrice(totalPrice);

                    ShoppingCart shoppingCart = shoppingCartRepository.save(cart);
                    return shoppingCartDTOMapper.apply(shoppingCart);
                }
            }
        }

        return null;
    }

    @Transactional
    public ShoppingCartDTO clearCart(Principal principal) {
        String email = principal.getName();

        Optional<User> userOptional = userRepository.findByEmail(email);
        if (userOptional.isPresent()) {
            // Get user cart
            User user = userOptional.get();
            Long userId = user.getId();
            ShoppingCart cart = shoppingCartRepository.findByBuyerId(userId);
            if (cart != null) {
                // Remove all items from the cart
                cartItemRepository.deleteAll(cart.getCartItems());
                cart.getCartItems().clear();

                // Update the cart's total price
                cart.setTotalPrice(0.0);

                // Save the updated cart
                ShoppingCart shoppingCart = shoppingCartRepository.save(cart);
                return shoppingCartDTOMapper.apply(shoppingCart);
            }
        }

        return null;
    }
}