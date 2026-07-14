package com.example.business.user.services;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.business.user.dtos.request.AddressCreateRequest;
import com.example.business.user.dtos.request.AddressUpdateRequest;
import com.example.business.user.dtos.response.AddressDeleteResponse;
import com.example.business.user.dtos.response.AddressResponse;
import com.example.business.user.entities.Address;
import com.example.business.user.exceptions.AddressNotFoundException;
import com.example.business.user.repositories.AddressRepository;

@Service
public class AddressService {
    private final AddressRepository addressRepository;
    private final UserService userService;

    public AddressService(AddressRepository addressRepository, UserService userService) {
        this.addressRepository = addressRepository;
        this.userService = userService;
    }

    @Transactional
    public AddressResponse createAddress(Long userId, AddressCreateRequest request) {
        userService.ensureUserExists(userId);

        if (request.isDefault()) {
            clearDefaultAddresses(userId);
        }

        Address address = new Address();
        address.setUserId(userId);
        address.setLabel(request.getLabel());
        address.setRecipientName(request.getRecipientName());
        address.setPhoneNumber(request.getPhoneNumber());
        address.setStreetAddress(request.getStreetAddress());
        address.setCity(request.getCity());
        address.setProvince(request.getProvince());
        address.setPostalCode(request.getPostalCode());
        address.setCountry(request.getCountry());
        address.setDefault(request.isDefault());

        Address saved = addressRepository.save(address);
        return toResponse(saved);
    }

    @Transactional
    public AddressResponse updateAddress(Long userId, Long addressId, AddressUpdateRequest request) {
        Address address = findAddressForUser(userId, addressId);

        if (request.getLabel() != null) {
            address.setLabel(request.getLabel());
        }
        if (request.getRecipientName() != null) {
            address.setRecipientName(request.getRecipientName());
        }
        if (request.getPhoneNumber() != null) {
            address.setPhoneNumber(request.getPhoneNumber());
        }
        if (request.getStreetAddress() != null) {
            address.setStreetAddress(request.getStreetAddress());
        }
        if (request.getCity() != null) {
            address.setCity(request.getCity());
        }
        if (request.getProvince() != null) {
            address.setProvince(request.getProvince());
        }
        if (request.getPostalCode() != null) {
            address.setPostalCode(request.getPostalCode());
        }
        if (request.getCountry() != null) {
            address.setCountry(request.getCountry());
        }
        if (Boolean.TRUE.equals(request.getIsDefault())) {
            clearDefaultAddresses(userId);
            address.setDefault(true);
        } else if (Boolean.FALSE.equals(request.getIsDefault())) {
            address.setDefault(false);
        }

        Address updated = addressRepository.save(address);
        return toResponse(updated);
    }

    @Transactional
    public AddressDeleteResponse deleteAddress(Long userId, Long addressId) {
        Address address = findAddressForUser(userId, addressId);
        addressRepository.delete(address);
        return new AddressDeleteResponse(address.getId(), "Address deleted successfully");
    }

    public List<AddressResponse> getAddressesByUserId(Long userId) {
        userService.ensureUserExists(userId);
        return addressRepository.findByUserId(userId)
            .stream()
            .map(this::toResponse)
            .toList();
    }

    public AddressResponse getAddressById(Long userId, Long addressId) {
        return toResponse(findAddressForUser(userId, addressId));
    }

    public Address findAddressForUser(Long userId, Long addressId) {
        return addressRepository.findByIdAndUserId(addressId, userId)
            .orElseThrow(() -> new AddressNotFoundException(addressId));
    }

    private void clearDefaultAddresses(Long userId) {
        addressRepository.findByUserId(userId).forEach(address -> {
            if (address.isDefault()) {
                address.setDefault(false);
                addressRepository.save(address);
            }
        });
    }

    private AddressResponse toResponse(Address address) {
        return new AddressResponse(
            address.getId(),
            address.getUserId(),
            address.getLabel(),
            address.getRecipientName(),
            address.getPhoneNumber(),
            address.getStreetAddress(),
            address.getCity(),
            address.getProvince(),
            address.getPostalCode(),
            address.getCountry(),
            address.isDefault()
        );
    }
}
