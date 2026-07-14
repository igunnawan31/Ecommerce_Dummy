package com.example.business.user.exceptions;

import com.example.business.common.exceptions.DuplicateResourceException;

public class SellerAlreadyRegisteredException extends DuplicateResourceException {
    public SellerAlreadyRegisteredException(String email) {
        super("User is already registered as seller: " + email);
    }
}
