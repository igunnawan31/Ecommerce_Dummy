package com.example.business.user.dtos.request;

import com.example.business.user.enums.UserRole;

public class UserCreateRequest {
    private String name;
    private String userName;
    private String email;
    private String password;
    private String phoneNumber;
    private String jenisKelamin;
    private String tanggalLahir;
    private String bioData;
    private Long profilePictureFileId;
    private UserRole role;

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

    public String getJenisKelamin() { return jenisKelamin; }
    public void setJenisKelamin(String jenisKelamin) { this.jenisKelamin = jenisKelamin; }

    public String getTanggalLahir() { return tanggalLahir; }
    public void setTanggalLahir(String tanggalLahir) { this.tanggalLahir = tanggalLahir; }

    public String getBioData() { return bioData; }
    public void setBioData(String bioData) { this.bioData = bioData; }

    public Long getProfilePictureFileId() { return profilePictureFileId; }
    public void setProfilePictureFileId(Long profilePictureFileId) { this.profilePictureFileId = profilePictureFileId; }

    public UserRole getRole() { return role; }
    public void setRole(UserRole role) { this.role = role; }
}
