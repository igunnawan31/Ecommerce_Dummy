package com.example.business.order.dtos.request;

public class OrderCreateRequest {
    private Long shippingAddressId;

    public Long getShippingAddressId() { return shippingAddressId; }
    public void setShippingAddressId(Long shippingAddressId) { this.shippingAddressId = shippingAddressId; }
}
