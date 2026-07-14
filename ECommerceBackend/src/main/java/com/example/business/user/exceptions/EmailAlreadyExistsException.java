package com.example.business.user.exceptions;

import com.example.business.common.exceptions.DuplicateResourceException;

public class EmailAlreadyExistsException extends DuplicateResourceException{
    public EmailAlreadyExistsException(String email) {
        super("Email already exists:" + email);
    }
}
