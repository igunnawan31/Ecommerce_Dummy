package com.example.business.user.exceptions;

import com.example.business.common.exceptions.ResourceNotFoundException;

public class UserNotFoundException extends ResourceNotFoundException {
    public UserNotFoundException(Long id) {
        super("User not found with id:" + id);
    }

    public UserNotFoundException(String email) {
        super("User not found with email:" + email);
    }
}
