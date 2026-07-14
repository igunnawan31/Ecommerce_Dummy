package com.example.business.common.exceptions;

public class DuplicateResourceException extends BaseException {
    public DuplicateResourceException(String message) {
        super(message, 409);
    }
}
