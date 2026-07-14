package com.example.business.order.exceptions;

import com.example.business.common.exceptions.ResourceNotFoundException;

public class OrderNotFoundException extends ResourceNotFoundException {
    public OrderNotFoundException(Long id) {
        super("Order not found with id: " + id);
    }
}
