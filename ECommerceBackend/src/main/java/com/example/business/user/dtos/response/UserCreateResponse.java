package com.example.business.user.dtos.response;

import java.util.List;

import com.example.business.user.enums.UserRole;

public class UserCreateResponse {
    private Long id;
    private String name;
    private String email;
    private String profilePictureUrl;
    private List<UserRole> roles;
    private String message;

    public UserCreateResponse(
        Long id,
        String name,
        String email,
        String profilePictureUrl,
        List<UserRole> roles,
        String message
    ) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.profilePictureUrl = profilePictureUrl;
        this.roles = roles;
        this.message = message;
    }

    public Long getId() { return id; }
    public String getName() { return name; }
    public String getEmail() { return email; }
    public String getProfilePictureUrl() { return profilePictureUrl; }
    public List<UserRole> getRoles() { return roles; }
    public String getMessage() { return message; }
}
