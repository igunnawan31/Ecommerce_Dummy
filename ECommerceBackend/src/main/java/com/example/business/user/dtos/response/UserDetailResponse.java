package com.example.business.user.dtos.response;

import java.util.List;

import com.example.business.user.enums.UserRole;

public class UserDetailResponse {
    private Long id;
    private String name;
    private String userName;
    private String email;
    private String phoneNumber;
    private String jenisKelamin;
    private String tanggalLahir;
    private String bioData;
    private String profilePictureUrl;
    private List<UserRole> roles;
    private Long storeId;

    public UserDetailResponse(
        Long id,
        String name,
        String userName,
        String email,
        String phoneNumber,
        String jenisKelamin,
        String tanggalLahir,
        String bioData,
        String profilePictureUrl,
        List<UserRole> roles,
        Long storeId
    ) {
        this.id = id;
        this.name = name;
        this.userName = userName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.jenisKelamin = jenisKelamin;
        this.tanggalLahir = tanggalLahir;
        this.bioData = bioData;
        this.profilePictureUrl = profilePictureUrl;
        this.roles = roles;
        this.storeId = storeId;
    }

    public Long getId() { return id; }
    public String getName() { return name; }
    public String getUserName() { return userName; }
    public String getEmail() { return email; }
    public String getPhoneNumber() { return phoneNumber; }
    public String getJenisKelamin() { return jenisKelamin; }
    public String getTanggalLahir() { return tanggalLahir; }
    public String getBioData() { return bioData; }
    public String getProfilePictureUrl() { return profilePictureUrl; }
    public List<UserRole> getRoles() { return roles; }
    public Long getStoreId() { return storeId; }
}
