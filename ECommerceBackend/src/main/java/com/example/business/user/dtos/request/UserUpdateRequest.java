package com.example.business.user.dtos.request;

public class UserUpdateRequest {
    private String name;
    private String userName;
    private String phoneNumber;
    private String jenisKelamin;
    private String tanggalLahir;
    private String bioData;
    private Long profilePictureFileId;

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getUserName() { return userName; }
    public void setUserName(String userName) { this.userName = userName; }

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
}
