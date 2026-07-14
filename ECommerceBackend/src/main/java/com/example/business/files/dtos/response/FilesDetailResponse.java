package com.example.business.files.dtos.response;

import com.example.business.files.enums.ReferenceType;

public class FilesDetailResponse {
    private Long id;

    private String fileName;
    private String filePath;
    private String fileType;
    private Long fileSize;
    
    private Long referenceId;
    private ReferenceType referenceType;

    public FilesDetailResponse(
        Long id, 
        String fileName, 
        String filePath,
        String fileType,
        Long fileSize,
        Long referenceId,
        ReferenceType referenceType
    ) {
        this.id = id;
        this.fileName = fileName;
        this.filePath = filePath;
        this.fileType = fileType;
        this.fileSize = fileSize;
        this.referenceId = referenceId;
        this.referenceType = referenceType;
    }

    public Long getId() {
        return id;
    }

    public String getFileName() {
        return fileName;
    }

    public String getFilePath() {
        return filePath;
    }

    public String getFileType() {
        return fileType;
    }

    public Long getFileSize() {
        return fileSize;
    }

    public Long getReferenceId() {
        return referenceId;
    }

    public ReferenceType getReferenceType() {
        return referenceType;
    }
}
