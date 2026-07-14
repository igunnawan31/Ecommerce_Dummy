package com.example.business.user.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.business.files.entities.File;
import com.example.business.files.enums.ReferenceType;
import com.example.business.files.exceptions.FileNotFoundException;
import com.example.business.files.repositories.FilesRepository;
import com.example.business.user.dtos.request.SellerSignupRequest;
import com.example.business.user.dtos.request.UserCreateRequest;
import com.example.business.user.dtos.request.UserUpdateRequest;
import com.example.business.user.dtos.response.UserAllResponse;
import com.example.business.user.dtos.response.UserCreateResponse;
import com.example.business.user.dtos.response.UserDeleteResponse;
import com.example.business.user.dtos.response.UserDetailResponse;
import com.example.business.user.dtos.response.UserUpdateResponse;
import com.example.business.user.entities.User;
import com.example.business.user.enums.UserRole;
import com.example.business.user.exceptions.EmailAlreadyExistsException;
import com.example.business.user.exceptions.InvalidSignupRoleException;
import com.example.business.user.exceptions.SellerAlreadyRegisteredException;
import com.example.business.user.exceptions.UserNotFoundException;
import com.example.business.user.exceptions.UsernameAlreadyExistsException;
import com.example.business.user.repositories.UserRepository;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final FilesRepository filesRepository;

    public UserService(UserRepository userRepository, FilesRepository filesRepository) {
        this.userRepository = userRepository;
        this.filesRepository = filesRepository;
    }

    @Transactional
    public UserCreateResponse createCustomer(UserCreateRequest request) {
        validateSignupRole(request.getRole(), true);
        UserRole assignedRole = resolveSignupRole(request.getRole(), UserRole.CUSTOMER);

        User user = buildUserFromRequest(request);
        user.setRoles(new ArrayList<>(List.of(assignedRole)));

        User saved = userRepository.save(user);
        linkProfilePicture(saved, request.getProfilePictureFileId());

        return toCreateResponse(saved, "Customer registered successfully");
    }

    @Transactional
    public UserCreateResponse registerSeller(SellerSignupRequest request) {
        if (userRepository.existsByEmail(request.getEmail())) {
            User existing = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new UserNotFoundException(request.getEmail()));

            if (existing.hasRole(UserRole.SELLER)) {
                throw new SellerAlreadyRegisteredException(request.getEmail());
            }

            existing.getRoles().add(UserRole.SELLER);
            if (request.getBioData() != null) {
                existing.setBioData(request.getBioData());
            }
            if (request.getPhoneNumber() != null) {
                existing.setPhoneNumber(request.getPhoneNumber());
            }
            linkProfilePicture(existing, request.getProfilePictureFileId());

            User saved = userRepository.save(existing);
            return toCreateResponse(saved, "Seller role added to existing customer");
        }

        validateUniqueCredentials(request.getEmail(), request.getUserName());

        User user = new User();
        user.setName(request.getName());
        user.setUserName(request.getUserName());
        user.setEmail(request.getEmail());
        user.setPassword(request.getPassword());
        user.setPhoneNumber(request.getPhoneNumber());
        user.setBioData(request.getBioData());
        user.setRoles(new ArrayList<>(List.of(UserRole.CUSTOMER, UserRole.SELLER)));

        User saved = userRepository.save(user);
        linkProfilePicture(saved, request.getProfilePictureFileId());

        return toCreateResponse(saved, "Seller registered successfully");
    }

    @Transactional
    public UserUpdateResponse updateUser(Long id, UserUpdateRequest request) {
        User user = userRepository.findById(id)
            .orElseThrow(() -> new UserNotFoundException(id));

        if (request.getName() != null) {
            user.setName(request.getName());
        }
        if (request.getUserName() != null) {
            if (!request.getUserName().equals(user.getUserName())
                && userRepository.existsByUserName(request.getUserName())) {
                throw new UsernameAlreadyExistsException(request.getUserName());
            }
            user.setUserName(request.getUserName());
        }
        if (request.getPhoneNumber() != null) {
            user.setPhoneNumber(request.getPhoneNumber());
        }
        if (request.getJenisKelamin() != null) {
            user.setJenisKelamin(request.getJenisKelamin());
        }
        if (request.getTanggalLahir() != null) {
            user.setTanggalLahir(request.getTanggalLahir());
        }
        if (request.getBioData() != null) {
            user.setBioData(request.getBioData());
        }
        if (request.getProfilePictureFileId() != null) {
            linkProfilePicture(user, request.getProfilePictureFileId());
        }

        User updated = userRepository.save(user);

        return new UserUpdateResponse(
            updated.getId(),
            updated.getName(),
            resolveProfilePictureUrl(updated.getProfilePictureFileId()),
            updated.getRoles(),
            "User updated successfully"
        );
    }

    @Transactional
    public UserDeleteResponse deleteUser(Long id) {
        User user = userRepository.findById(id)
            .orElseThrow(() -> new UserNotFoundException(id));

        userRepository.delete(user);

        return new UserDeleteResponse(user.getId(), "User deleted successfully");
    }

    public List<UserAllResponse> getAllUsers() {
        return userRepository.findAll()
            .stream()
            .map(this::toAllResponse)
            .toList();
    }

    public UserDetailResponse getUserById(Long id) {
        User user = userRepository.findById(id)
            .orElseThrow(() -> new UserNotFoundException(id));

        return toDetailResponse(user);
    }

    public void ensureUserExists(Long userId) {
        if (!userRepository.existsById(userId)) {
            throw new UserNotFoundException(userId);
        }
    }

    private User buildUserFromRequest(UserCreateRequest request) {
        validateUniqueCredentials(request.getEmail(), request.getUserName());

        User user = new User();
        user.setName(request.getName());
        user.setUserName(request.getUserName());
        user.setEmail(request.getEmail());
        user.setPassword(request.getPassword());
        user.setPhoneNumber(request.getPhoneNumber());
        user.setJenisKelamin(request.getJenisKelamin());
        user.setTanggalLahir(request.getTanggalLahir());
        user.setBioData(request.getBioData());
        return user;
    }

    private void validateUniqueCredentials(String email, String userName) {
        if (email != null && userRepository.existsByEmail(email)) {
            throw new EmailAlreadyExistsException(email);
        }
        if (userName != null && userRepository.existsByUserName(userName)) {
            throw new UsernameAlreadyExistsException(userName);
        }
    }

    private void validateSignupRole(UserRole role, boolean customerOnly) {
        if (role == null) {
            return;
        }
        if (role == UserRole.ADMIN || role == UserRole.SUPERADMIN) {
            throw InvalidSignupRoleException.adminNotAllowed();
        }
        if (customerOnly && role == UserRole.SELLER) {
            throw InvalidSignupRoleException.useSellerEndpoint();
        }
        if (role != UserRole.CUSTOMER && role != UserRole.SELLER) {
            throw InvalidSignupRoleException.adminNotAllowed();
        }
    }

    private UserRole resolveSignupRole(UserRole role, UserRole defaultRole) {
        if (role == null || role == UserRole.CUSTOMER) {
            return defaultRole;
        }
        return role;
    }

    private void linkProfilePicture(User user, Long fileId) {
        if (fileId == null) {
            return;
        }

        File file = filesRepository.findById(fileId)
            .orElseThrow(() -> new FileNotFoundException(fileId));

        file.setReferenceId(user.getId());
        file.setReferenceType(ReferenceType.USER);
        filesRepository.save(file);

        user.setProfilePictureFileId(file.getId());
    }

    private String resolveProfilePictureUrl(Long fileId) {
        if (fileId == null) {
            return null;
        }
        return filesRepository.findById(fileId)
            .map(File::getFilePath)
            .orElse(null);
    }

    private UserCreateResponse toCreateResponse(User user, String message) {
        return new UserCreateResponse(
            user.getId(),
            user.getName(),
            user.getEmail(),
            resolveProfilePictureUrl(user.getProfilePictureFileId()),
            user.getRoles(),
            message
        );
    }

    private UserAllResponse toAllResponse(User user) {
        return new UserAllResponse(
            user.getId(),
            user.getName(),
            user.getUserName(),
            user.getEmail(),
            resolveProfilePictureUrl(user.getProfilePictureFileId()),
            user.getRoles()
        );
    }

    private UserDetailResponse toDetailResponse(User user) {
        return new UserDetailResponse(
            user.getId(),
            user.getName(),
            user.getUserName(),
            user.getEmail(),
            user.getPhoneNumber(),
            user.getJenisKelamin(),
            user.getTanggalLahir(),
            user.getBioData(),
            resolveProfilePictureUrl(user.getProfilePictureFileId()),
            user.getRoles(),
            user.getStoreId()
        );
    }
}
