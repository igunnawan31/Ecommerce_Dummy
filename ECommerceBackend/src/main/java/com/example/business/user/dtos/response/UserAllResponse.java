package com.example.business.user.dtos.response;

import java.util.List;

import com.example.business.user.enums.UserRole;

public class UserAllResponse {
    private Long id;
    private String name;
    private String userName;
    private String email;
    private String profilePictureUrl;
    private List<UserRole> roles;

    public UserAllResponse(
        Long id,
        String name,
        String userName,
        String email,
        String profilePictureUrl,
        List<UserRole> roles
    ) {
        this.id = id;
        this.name = name;
        this.userName = userName;
        this.email = email;
        this.profilePictureUrl = profilePictureUrl;
        this.roles = roles;
    }

    public Long getId() { return id; }
    public String getName() { return name; }
    public String getUserName() { return userName; }
    public String getEmail() { return email; }
    public String getProfilePictureUrl() { return profilePictureUrl; }
    public List<UserRole> getRoles() { return roles; }
}
