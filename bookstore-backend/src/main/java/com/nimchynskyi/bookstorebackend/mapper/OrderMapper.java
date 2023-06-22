package com.nimchynskyi.bookstorebackend.mapper;




import com.nimchynskyi.bookstorebackend.dto.OrderDto;
import com.nimchynskyi.bookstorebackend.Cart;
import com.nimchynskyi.bookstorebackend.CartItem;
import com.nimchynskyi.bookstorebackend.model.Order;
import com.nimchynskyi.bookstorebackend.model.OrderItem;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class OrderMapper {

    public static Order mapToOrder(OrderDto orderDto) {
        return Order.builder()
                .firstName(orderDto.getFirstName())
                .lastName(orderDto.getLastName())
                .address(orderDto.getAddress())
                .postCode(orderDto.getPostCode())
                .city(orderDto.getCity())
                .created(LocalDateTime.now())
                .build();
    }

    public static List<OrderItem> mapToOrderItemList(Cart cart, Order order) {
        List<OrderItem> orderItems = new ArrayList<>();
        for(CartItem ci: cart.getCartItems()) {
            orderItems.add(new OrderItem(order.getOrderId(), ci.getItem().getId(), ci.getCounter()));
        }
        return orderItems;
    }
}
