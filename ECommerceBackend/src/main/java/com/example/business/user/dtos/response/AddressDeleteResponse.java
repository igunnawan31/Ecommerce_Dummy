package com.example.business.user.dtos.response;

public class AddressDeleteResponse {
    private Long id;
    private String message;

    public AddressDeleteResponse(Long id, String message) {
        this.id = id;
        this.message = message;
    }

    public Long getId() { return id; }
    public String getMessage() { return message; }
}
