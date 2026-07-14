package com.example.business.common.exceptions;

public class ForbiddenException extends BaseException {
    public ForbiddenException(String message) {
        super(message, 403);
    }
}
