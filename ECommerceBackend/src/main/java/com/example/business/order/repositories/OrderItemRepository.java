package com.example.business.order.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.business.order.entities.OrderItem;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
    List<OrderItem> findByOrderId(Long orderId);
}
