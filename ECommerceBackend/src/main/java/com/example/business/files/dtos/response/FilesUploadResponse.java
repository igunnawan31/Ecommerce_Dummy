package com.example.business.files.dtos.response;

public class FilesUploadResponse {
    private Long id;

    private String fileName;
    private String filePath;
    private String fileType;
    private Long fileSize;

    private String message;

    public FilesUploadResponse(
        Long id, 
        String fileName, 
        String filePath,
        String fileType,
        Long fileSize,
        String message
    ) {
        this.id = id;
        this.fileName = fileName;
        this.filePath = filePath;
        this.fileType = fileType;
        this.fileSize = fileSize;
        this.message = message;
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

    public String getMessage() {
        return message;
    }
}
