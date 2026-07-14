package com.example.business.files.dtos.response;

public class FilesDeleteResponse {
    private Long id;
    private String message;

    public FilesDeleteResponse(Long id, String message) {
        this.id = id;
        this.message = message;
    }

    public Long getId() {
        return id;
    }

    public String getMessage() {
        return message;
    }
}
