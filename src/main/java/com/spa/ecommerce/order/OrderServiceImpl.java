package com.spa.ecommerce.order;

import com.spa.ecommerce.exception.order.CancelOrderException;
import com.spa.ecommerce.exception.order.OrderNotFoundException;
import com.spa.ecommerce.notificationService.EmailServiceImpl;
import com.spa.ecommerce.order.dto.OrderDTO;
import com.spa.ecommerce.order.dto.OrderDTOMapper;
import com.spa.ecommerce.order.orderitem.OrderItem;
import com.spa.ecommerce.product.entity.Product;
import com.spa.ecommerce.product.repository.ProductRepository;
import com.spa.ecommerce.role.Role;
import com.spa.ecommerce.seller.SellerRepository;
import com.spa.ecommerce.shoppingcart.CartItem.entity.CartItem;
import com.spa.ecommerce.shoppingcart.CartItem.repository.CartItemRepository;
import com.spa.ecommerce.shoppingcart.ShoppingCart;
import com.spa.ecommerce.shoppingcart.ShoppingCartRepository;
//import com.spa.ecommerce.user.SellerRepository;
import com.spa.ecommerce.user.User;
import com.spa.ecommerce.user.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.time.LocalDate;
import java.util.Collection;
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
    private CartItemRepository cartItemRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    SellerRepository sellerRepository;
    @Autowired
    private EmailServiceImpl emailService;

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

                order = orderRepository.save(order);


                productRepository.saveAll(cart.getCartItems().stream().map(CartItem::getProduct).toList());

                cartItemRepository.deleteAll(cart.getCartItems());
                cart.getCartItems().clear();
                cart.setTotalPrice(0.0);
                shoppingCartRepository.save(cart);

                emailService.sendOrderConfirmationEmail(user, order);

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


    @Override
    public OrderDTO cancelOrder(Principal principal, long orderId) {
        String email = principal.getName();
        Optional<User> userOptional = userRepository.findByEmail(email);

        if (userOptional.isPresent()) {
            User user = userOptional.get();
            Collection<Role> roles = user.getRoles();

            for (Role role : roles) {
                if ("ROLE_BUYER".equals(role.getName())) {
                    List<Order> orders = orderRepository.findByBuyerId(user.getId());

                    Order order = orders.stream()
                            .filter(o -> o.getId().equals(orderId))
                            .findFirst()
                            .orElseThrow(() -> new RuntimeException("Order not found"));

                    if (order.getStatus() == Status.PROCESSING || order.getStatus() == Status.PENDING) {
                        order.setStatus(Status.CANCELLED);
                        Order updatedOrder = orderRepository.save(order);
                        emailService.sendOrderCancellationEmail (user, order);
                        return orderDTOMapper.apply(updatedOrder);
                    } else {
                        throw new CancelOrderException("Order cannot be cancelled", "ORDER_CANNOT_BE_CANCELLED");
                    }

                }
                else if ("ROLE_SELLER".equals(role.getName())) {

                    Optional<List<OrderItem>> optionalOrderItems = sellerRepository.findOrderItemsByUserId(user.getId());
                    if (!optionalOrderItems.isPresent()) {
                        throw new RuntimeException("No order items found for the user");
                    }

                    List<OrderItem> orderItems = optionalOrderItems.get();


                    OrderItem orderItem = orderItems.stream()
                            .filter(oi -> oi.getOrder().getId().equals(orderId))
                            .findFirst()
                            .orElseThrow(() ->  new OrderNotFoundException("Order not found", "ORDER_NOT_FOUND"));

                    Order order = orderItem.getOrder();
                    if (order.getStatus() == Status.PROCESSING || order.getStatus() == Status.PENDING) {
                        order.setStatus(Status.CANCELLED);
                        Order updatedOrder = orderRepository.save(order);
                        emailService.sendOrderCancellationEmail (user, order);
                        return orderDTOMapper.apply(updatedOrder);
                    } else {
                        throw new CancelOrderException("Order cannot be cancelled", "ORDER_CANNOT_BE_CANCELLED");
                    }
                }

            }
        }
        return null;
    }

    @Override
    public OrderDTO updateOrderStatus(Principal principal, long orderId) {
        String email = principal.getName();
        Optional<User> userOptional = userRepository.findByEmail(email);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            Collection<Role> roles = user.getRoles();

            for (Role role : roles) {
                if ("ROLE_SELLER".equals(role.getName())) {
                    Optional<List<OrderItem>> optionalOrderItems = sellerRepository.findOrderItemsByUserId(user.getId());

                    if (optionalOrderItems.isPresent()) {
                        List<OrderItem> orderItems = optionalOrderItems.get();

                        Order order = orderItems.stream()
                                .map(OrderItem::getOrder)
                                .filter(o -> o.getId().equals(orderId))
                                .findFirst()
                                .orElseThrow(() ->  new OrderNotFoundException("Order not found", "ORDER_NOT_FOUND"));

                        if (order.getStatus() == Status.PENDING) {
                            order.setStatus(Status.PROCESSING);
                        } else if (order.getStatus() == Status.PROCESSING) {
                            order.setStatus(Status.SHIPPED);
                        } else if (order.getStatus() == Status.SHIPPED) {
                            order.setStatus(Status.DELIVERED);
                        } else {
                            throw new RuntimeException("Order status cannot be updated");
                        }

                        Order updatedOrder = orderRepository.save(order);


                        emailService.sendOrderStatusUpdateEmail(user, order);

                        return orderDTOMapper.apply(updatedOrder);
                    } else {
                        throw  new OrderNotFoundException("Order not found", "ORDER_NOT_FOUND");
                    }
                } else {
                    throw new RuntimeException("User not found");
                }
            }
        }
            return null;



        }
    }

