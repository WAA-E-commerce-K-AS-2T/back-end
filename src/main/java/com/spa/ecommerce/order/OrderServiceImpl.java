package com.spa.ecommerce.order;

import com.spa.ecommerce.order.dto.OrderDTO;
import com.spa.ecommerce.order.dto.OrderDTOMapper;
import com.spa.ecommerce.order.orderitem.ItemRepository;
import com.spa.ecommerce.order.orderitem.OrderItem;
import com.spa.ecommerce.order.orderitem.dto.OrderItemDTOMapper;
import com.spa.ecommerce.product.entity.Product;
import com.spa.ecommerce.product.repository.ProductRepository;
import com.spa.ecommerce.shoppingcart.CartItem.entity.CartItem;
import com.spa.ecommerce.shoppingcart.CartItem.repository.CartItemRepository;
import com.spa.ecommerce.shoppingcart.ShoppingCart;
import com.spa.ecommerce.shoppingcart.ShoppingCartRepository;
import com.spa.ecommerce.user.User;
import com.spa.ecommerce.user.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderDTOMapper orderDTOMapper;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ShoppingCartRepository shoppingCartRepository;
    @Autowired
    private ItemRepository itemRepository;
    @Autowired
    private CartItemRepository cartItemRepository;
    @Autowired
    private ProductRepository productRepository;

    @Transactional
    public OrderDTO placeOrder(Principal principal, OrderDTO orderDTO) {
        String email = principal.getName();
        Optional<User> userOptional = userRepository.findByEmail(email);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            ShoppingCart cart = shoppingCartRepository.findByBuyerId(user.getId());
            if (!cart.getCartItems().isEmpty()) {
                Order order = new Order();
                order.setBuyer(user);
                order.setDate(LocalDate.now());
                order.setStatus(Status.PENDING);
                order.setAmount(cart.getTotalPrice());
                order.setPaymentMethod(orderDTO.getPaymentMethod());

                for (CartItem cartItem : cart.getCartItems()) {
                    OrderItem orderItem = new OrderItem();
                    orderItem.setOrder(order);
                    orderItem.setQuantity(cartItem.getQuantity());
                    orderItem.setProduct(cartItem.getProduct());
                    orderItem.setPrice(cartItem.getPrice());

                    Product product = cartItem.getProduct();
                    product.setInStock(product.getInStock() - orderItem.getQuantity());
                    product.setTimesBought(product.getTimesBought() + orderItem.getQuantity());

                    order.getOrderItems().add(orderItem);
                }

                order = orderRepository.save(order); // Save order with order items due to cascading

                // Ensure products are saved
                productRepository.saveAll(cart.getCartItems().stream().map(CartItem::getProduct).toList());

                cartItemRepository.deleteAll(cart.getCartItems());
                cart.getCartItems().clear();
                cart.setTotalPrice(0.0);
                shoppingCartRepository.save(cart);

                return orderDTOMapper.apply(order);
            }
        }
        throw new RuntimeException("Order Cannot be placed");
    }


    @Override
    public OrderDTO findById(long id) {
        return orderRepository.findById(id)
                .map(orderDTOMapper)
                .orElse(null);
    }

//    @Override
//    public Optional<OrderDTO> update(long id) {
//        Order existingOrder = orderRepository.findById(id).orElse(null);
//        Status currentStatus = existingOrder.getStatus();
//        if(currentStatus == Status.PENDING || currentStatus == Status.PROCESSING){
//            existingOrder.setStatus(Status.CANCELLED);
//            orderRepository.save(existingOrder);
//            return Optional.of(orderDTOMapper.apply(existingOrder));
//        }else {
//            return Optional.empty();
//        }
//
//
//    }

    @Override
    public List<OrderDTO> findAll(Principal principal) {
        String email = principal.getName();
        Optional<User> userOptional = userRepository.findByEmail(email);
        if (userOptional.isPresent()) {
            List<Order> orders = orderRepository.findByBuyerId(userOptional.get().getId());
            return orders
                    .stream()
                    .map(orderDTOMapper)
                    .collect(Collectors.toList());
        }
        return null;
    }
}
