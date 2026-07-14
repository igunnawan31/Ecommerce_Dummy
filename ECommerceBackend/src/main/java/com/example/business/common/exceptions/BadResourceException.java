package com.example.business.common.exceptions;

public class BadResourceException extends BaseException {
    public BadResourceException(String message) {
        super(message, 400);
    }
}
