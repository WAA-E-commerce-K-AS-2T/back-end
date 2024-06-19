package com.spa.ecommerce.order;

import com.spa.ecommerce.order.dto.OrderDTO;
import com.spa.ecommerce.order.dto.OrderDTOMapper;
import com.spa.ecommerce.product.entity.Product;
import com.spa.ecommerce.shoppingcart.ShoppingCart;
import com.spa.ecommerce.shoppingcart.ShoppingCartDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    private ItemRepository itemRepository;
    @Autowired
    private CartItemRepository cartItemRepository;
    @Autowired
    private ProductRepository productRepository;

    @Override
    public Collection<OrderDTO> getAll() {
        return orderRepository.findAll().stream().map(orderDTOMapper).collect(Collectors.toList());
    }


    @Override
    public Optional<OrderDTO> get(Long id) {
        return orderRepository.findById(id).map(orderDTOMapper);
    }

    @Override
    public Optional<OrderDTO> save(OrderDTO entity) {
        Order order = new Order();
        order.setStatus(entity.getStatus());
        order = orderRepository.save(order);
        return Optional.of(orderDTOMapper.apply(order));
    }

    @Override
    public Optional<OrderDTO> update(Long id, OrderDTO entity) {
        Optional<Order> existingOrderOpt = orderRepository.findById(id);
        if (existingOrderOpt.isPresent()) {
            Order existingOrder = existingOrderOpt.get();
            existingOrder.setStatus(entity.getStatus());
            orderRepository.save(existingOrder);
            return Optional.of(orderDTOMapper.apply(existingOrder));
        } else {
            return Optional.empty();
        }
    }

    @Override
    public Optional<OrderDTO> delete(Long id, OrderDTO entity) {
        Optional<Order> existingOrderOpt = orderRepository.findById(id);
        if (existingOrderOpt.isPresent()) {
            orderRepository.deleteById(id);
            return Optional.of(orderDTOMapper.apply(existingOrderOpt.get()));
        } else {
            return Optional.empty();
        }
    }
}
