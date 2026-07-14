package com.example.business.files.controllers;

import java.io.IOException;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.business.dtos.response.ApiResponse;
import com.example.business.files.dtos.request.FilesUpdateRequest;
import com.example.business.files.dtos.request.FilesUploadRequest;
import com.example.business.files.dtos.request.FilesByPathRequest;
import com.example.business.files.dtos.request.FilesByReferenceIdRequest;
import com.example.business.files.dtos.response.FilesByPathResponse;
import com.example.business.files.dtos.response.FilesByReferenceIdResponse;
import com.example.business.files.dtos.response.FilesDeleteResponse;
import com.example.business.files.dtos.response.FilesDetailResponse;
import com.example.business.files.dtos.response.FilesUpdateResponse;
import com.example.business.files.dtos.response.FilesUploadResponse;
import com.example.business.files.services.FilesService;

@RestController
@RequestMapping("/api/files")
public class FilesController {
    private final FilesService filesService;

    public FilesController(FilesService filesService) {
        this.filesService = filesService;
    }

    @PostMapping("/upload")
    public ApiResponse<FilesUploadResponse> uploadFile(@ModelAttribute FilesUploadRequest request) throws IOException {
        return ApiResponse.success(
            "File uploaded successfully",
            filesService.uploadFile(request)
        );
    }

    @PutMapping("/update")
    public ApiResponse<FilesUpdateResponse> updateFile(@ModelAttribute FilesUpdateRequest request) throws IOException {
        return ApiResponse.success(
            "File updated successfully",
            filesService.updateFile(request)
        );
    }

    @DeleteMapping("/{id}")
    public ApiResponse<FilesDeleteResponse> deleteFile(@PathVariable Long id) {
        return ApiResponse.success(
            "File deleted successfully",
            filesService.deleteFile(id)
        );
    }

    @GetMapping("/{id}")
    public ApiResponse<FilesDetailResponse> getFileById(@PathVariable Long id) {
        return ApiResponse.success(
            "File retrieved successfully",
            filesService.getFileById(id)
        );
    }
    
    @GetMapping("/path")
    public ApiResponse<FilesByPathResponse> getFileByPath(@ModelAttribute FilesByPathRequest request) {
        return ApiResponse.success(
            "File retrieved successfully",
            filesService.getFileByPath(request)
        );
    }

    @GetMapping("/reference")
    public ApiResponse<FilesByReferenceIdResponse> getFileByReferenceId(@ModelAttribute FilesByReferenceIdRequest request) {
        return ApiResponse.success(
            "File retrieved successfully",
            filesService.getFilesByReferenceId(request)
        );
    }
}
