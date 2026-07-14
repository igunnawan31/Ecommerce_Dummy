package com.example.business.user.dtos.response;

public class AddressResponse {
    private Long id;
    private Long userId;
    private String label;
    private String recipientName;
    private String phoneNumber;
    private String streetAddress;
    private String city;
    private String province;
    private String postalCode;
    private String country;
    private boolean isDefault;

    public AddressResponse(
        Long id,
        Long userId,
        String label,
        String recipientName,
        String phoneNumber,
        String streetAddress,
        String city,
        String province,
        String postalCode,
        String country,
        boolean isDefault
    ) {
        this.id = id;
        this.userId = userId;
        this.label = label;
        this.recipientName = recipientName;
        this.phoneNumber = phoneNumber;
        this.streetAddress = streetAddress;
        this.city = city;
        this.province = province;
        this.postalCode = postalCode;
        this.country = country;
        this.isDefault = isDefault;
    }

    public Long getId() { return id; }
    public Long getUserId() { return userId; }
    public String getLabel() { return label; }
    public String getRecipientName() { return recipientName; }
    public String getPhoneNumber() { return phoneNumber; }
    public String getStreetAddress() { return streetAddress; }
    public String getCity() { return city; }
    public String getProvince() { return province; }
    public String getPostalCode() { return postalCode; }
    public String getCountry() { return country; }
    public boolean isDefault() { return isDefault; }
}
