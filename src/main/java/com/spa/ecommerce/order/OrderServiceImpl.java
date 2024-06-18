package com.spa.ecommerce.order;

import com.spa.ecommerce.order.dto.OrderDTO;
import com.spa.ecommerce.order.dto.OrderDTOMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderDTOMapper orderDTOMapper;

    @Override
    public OrderDTO save(Order order) {
        Order savedOrder = orderRepository.save(order);
        return orderDTOMapper.apply(savedOrder);
    }

    @Override
    public OrderDTO findById(int id) {
        return orderRepository.findById(id)
                .map(orderDTOMapper)
                .orElse(null);
    }

    @Override
    public void update(int id, Order order) {
        Order existingOrder = orderRepository.findById(id).orElse(null);
        if (existingOrder != null) {
            orderRepository.save(order);
        }
    }

    @Override
    public void delete(int id) {
        orderRepository.deleteById(id);
    }

    @Override
    public List<OrderDTO> findAll() {
        return orderRepository.findAll()
                .stream()
                .map(orderDTOMapper)
                .collect(Collectors.toList());
    }
}
