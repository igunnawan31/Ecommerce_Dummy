package com.example.business.files.dtos.request;

import org.springframework.web.multipart.MultipartFile;

import com.example.business.files.enums.ReferenceType;

public class FilesUpdateRequest {
    private Long id;
    private MultipartFile file;
    private Long referenceId;
    private ReferenceType referenceType;

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public MultipartFile getFile() {
        return file;
    }
    public void setFile(MultipartFile file) {
        this.file = file;
    }

    public Long getReferenceId() {
        return referenceId;
    }
    public void setReferenceId(Long referenceId) {
        this.referenceId = referenceId;
    }

    public ReferenceType getReferenceType() {
        return referenceType;
    }
    public void setReferenceType(ReferenceType referenceType) {
        this.referenceType = referenceType;
    }
}
