package com.example.business.cart.exceptions;

import com.example.business.common.exceptions.BadResourceException;

public class EmptyCartException extends BadResourceException {
    public EmptyCartException(Long userId) {
        super("Cart is empty for user: " + userId);
    }
}
