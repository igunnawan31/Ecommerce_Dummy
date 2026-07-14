package com.example.business.files.dtos.response;

public class FilesByReferenceIdResponse {
    private Long id;
    private String fileName;
    private String filePath;

    public FilesByReferenceIdResponse(Long id, String fileName, String filePath) {
        this.id = id;
        this.fileName = fileName;
        this.filePath = filePath;
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
}
