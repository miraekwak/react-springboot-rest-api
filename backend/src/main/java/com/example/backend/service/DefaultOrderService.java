package com.example.backend.service;

import com.example.backend.model.*;
import com.example.backend.repository.MemberRepository;
import com.example.backend.repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class DefaultOrderService implements OrderService{

    private final OrderRepository orderRepository;
    private final MemberRepository memberRepository;

    public DefaultOrderService(OrderRepository orderRepository, MemberRepository memberRepository) {
        this.orderRepository = orderRepository;
        this.memberRepository = memberRepository;
    }

    @Override
    public Order createOrder(UUID memberId, Email email, String address, String postcode, List<OrderItem> orderItems) {
        Order order = new Order(
                UUID.randomUUID(), memberId, email, address, postcode, orderItems,
                OrderStatus.ACCEPTED, LocalDateTime.now(), LocalDateTime.now());
        return orderRepository.insert(order);
    }

    @Override
    public List<Order> getOrderList(String username) {
        var member = memberRepository.findByUsername(username).orElseThrow(() -> new RuntimeException("존재하지 않는 사용자입니다."));
        return orderRepository.findAllByMemberId(member.getMemberId());
    }

    @Override
    public UUID deleteOrder(UUID orderId) {
        orderRepository.delete(orderId);
        return orderId;
    }


}
