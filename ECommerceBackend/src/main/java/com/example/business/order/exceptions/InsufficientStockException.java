package com.example.business.order.exceptions;

import com.example.business.common.exceptions.BadResourceException;

public class InsufficientStockException extends BadResourceException {
    public InsufficientStockException(Long productId, int requested, int available) {
        super("Insufficient stock for product " + productId + ": requested " + requested + ", available " + available);
    }
}
