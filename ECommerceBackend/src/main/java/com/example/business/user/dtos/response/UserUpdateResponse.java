package com.example.business.user.dtos.response;

public class UserUpdateResponse {
    private Long id;
    private String name;
    private String message;
    
    public UserUpdateResponse(Long id, String name, String message) {
        this.id = id;
        this.name = name;
        this.message = message;
    }

    public Long getId() { 
        return id; 
    }
    
    public String getName() { 
        return name; 
    }

    public String getMessage() { 
        return message; 
    }
}
