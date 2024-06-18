package com.spa.ecommerce.orderitem;

import com.spa.ecommerce.orderitem.dto.OrderItemDTO;
import com.spa.ecommerce.orderitem.dto.OrderItemDTOMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ItemServiceImpl implements ItemService {

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private OrderItemDTOMapper itemDTOMapper;

    @Override
    public OrderItemDTO save(OrderItem orderItem) {
        OrderItem savedOrder = itemRepository.save(orderItem);
        return itemDTOMapper.apply(savedOrder);
    }

    @Override
    public OrderItemDTO findById(int id) {
        return itemRepository.findById(id)
                .map(itemDTOMapper)
                .orElse(null);
    }

    @Override
    public void update(int id, OrderItem order) {
        OrderItem existingOrder = itemRepository.findById(id).orElse(null);
        if (existingOrder != null) {
            itemRepository.save(order);
        }
    }

    @Override
    public void delete(int id) {
        itemRepository.deleteById(id);
    }

    @Override
    public List<OrderItemDTO> findAll() {
        return itemRepository.findAll()
                .stream()
                .map(itemDTOMapper)
                .collect(Collectors.toList());
    }
}
