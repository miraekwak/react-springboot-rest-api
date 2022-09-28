package com.example.backend.repository;

import com.example.backend.model.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface OrderRepository {

    List<Order> findAll();

    Order insert(Order order);

    Order update(Order order);

    Optional<Order> findById(UUID orderId);

    Optional<Order> findByEmail(Email email);

    Optional<Order> findByOrderStatus(OrderStatus orderStatus);

    void delete(UUID orderId);

}
