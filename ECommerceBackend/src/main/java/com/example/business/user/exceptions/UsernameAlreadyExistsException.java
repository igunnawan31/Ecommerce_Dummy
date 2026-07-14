package com.example.business.user.exceptions;

import com.example.business.common.exceptions.DuplicateResourceException;

public class UsernameAlreadyExistsException extends DuplicateResourceException {
    public UsernameAlreadyExistsException(String username) {
        super("Username already exists:" + username);
    }
}
