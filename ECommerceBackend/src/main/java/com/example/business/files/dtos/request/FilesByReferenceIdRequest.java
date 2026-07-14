package com.example.business.files.dtos.request;

import com.example.business.files.enums.ReferenceType;

public class FilesByReferenceIdRequest {
    private Long referenceId;
    private ReferenceType referenceType;

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
