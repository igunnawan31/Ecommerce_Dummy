package com.example.business.cart.exceptions;

import com.example.business.common.exceptions.ResourceNotFoundException;

public class CartItemNotFoundException extends ResourceNotFoundException {
    public CartItemNotFoundException(Long id) {
        super("Cart item not found with id: " + id);
    }
}
