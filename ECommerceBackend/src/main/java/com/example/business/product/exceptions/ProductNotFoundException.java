package com.example.business.product.exceptions;

import com.example.business.common.exceptions.ResourceNotFoundException;

public class ProductNotFoundException extends ResourceNotFoundException {
    public ProductNotFoundException(Long id) {
        super("Product not found with id: " + id);
    }
}
