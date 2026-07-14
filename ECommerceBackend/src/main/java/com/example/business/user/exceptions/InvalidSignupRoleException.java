package com.example.business.user.exceptions;

import com.example.business.common.exceptions.BadResourceException;

public class InvalidSignupRoleException extends BadResourceException {
    public InvalidSignupRoleException(String message) {
        super(message);
    }

    public static InvalidSignupRoleException adminNotAllowed() {
        return new InvalidSignupRoleException("ADMIN and SUPERADMIN roles cannot be assigned during signup");
    }

    public static InvalidSignupRoleException useSellerEndpoint() {
        return new InvalidSignupRoleException("Use POST /api/users/seller to register as a seller");
    }
}
