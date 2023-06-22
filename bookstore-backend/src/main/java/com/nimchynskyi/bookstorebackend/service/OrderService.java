package com.nimchynskyi.bookstorebackend.service;

import com.nimchynskyi.bookstorebackend.dto.OrderDto;
import com.nimchynskyi.bookstorebackend.mapper.OrderMapper;
import com.nimchynskyi.bookstorebackend.Cart;
import com.nimchynskyi.bookstorebackend.model.Order;
import com.nimchynskyi.bookstorebackend.repository.OrderItemRepository;
import com.nimchynskyi.bookstorebackend.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class OrderService {

    private final Cart cart;
    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;
    private final UserService userService;

    @Autowired
    public OrderService(Cart cart, OrderRepository orderRepository, OrderItemRepository orderItemRepository, UserService userService) {
        this.cart = cart;
        this.orderRepository = orderRepository;
        this.orderItemRepository = orderItemRepository;
        this.userService = userService;
    }

    public void saveOrder(OrderDto orderDto, String name) {
        Order order = OrderMapper.mapToOrder(orderDto);
        orderRepository.save(order);
        userService.addOrder(order, name);

        orderItemRepository.saveAll(OrderMapper.mapToOrderItemList(cart, order));
        cart.cleanCart();
    }

    public void realizeById(Long orderId) {
       var order = orderRepository.findById(orderId);
        if (!order.isPresent()){
            return;
        }
        order.get().setRealized(true);
        orderRepository.save(order.get());
        userService.removeOrder(order.get());
    }
}
