package com.example.backend.service;

import com.example.backend.model.Email;
import com.example.backend.model.Order;
import com.example.backend.model.OrderItem;

import java.util.List;
import java.util.UUID;

public interface OrderService {

    Order createOrder(UUID memberId, Email email, String address, String postcode, List<OrderItem> orderItems);

    List<Order> getOrderList();

    UUID deleteOrder(UUID orderId);

}
