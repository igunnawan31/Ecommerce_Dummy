package com.example.business.user.exceptions;

import com.example.business.common.exceptions.ResourceNotFoundException;

public class AddressNotFoundException extends ResourceNotFoundException {
    public AddressNotFoundException(Long id) {
        super("Address not found with id: " + id);
    }
}
