package com.example.business.user.dtos.request;

public class SellerSignupRequest {
    private String name;
    private String userName;
    private String email;
    private String password;
    private String phoneNumber;
    private String bioData;
    private Long profilePictureFileId;

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getUserName() { return userName; }
    public void setUserName(String userName) { this.userName = userName; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getPhoneNumber() { return phoneNumber; }
    public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }

    public String getBioData() { return bioData; }
    public void setBioData(String bioData) { this.bioData = bioData; }

    public Long getProfilePictureFileId() { return profilePictureFileId; }
    public void setProfilePictureFileId(Long profilePictureFileId) { this.profilePictureFileId = profilePictureFileId; }
}
