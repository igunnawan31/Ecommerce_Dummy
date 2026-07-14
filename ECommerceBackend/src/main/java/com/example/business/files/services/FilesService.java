package com.example.business.files.services;

import java.io.IOException;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.business.files.dtos.request.FilesByPathRequest;
import com.example.business.files.dtos.request.FilesByReferenceIdRequest;
import com.example.business.files.dtos.request.FilesUpdateRequest;
import com.example.business.files.dtos.request.FilesUploadRequest;
import com.example.business.files.dtos.response.FilesByPathResponse;
import com.example.business.files.dtos.response.FilesByReferenceIdResponse;
import com.example.business.files.dtos.response.FilesDeleteResponse;
import com.example.business.files.dtos.response.FilesDetailResponse;
import com.example.business.files.dtos.response.FilesUpdateResponse;
import com.example.business.files.dtos.response.FilesUploadResponse;
import com.example.business.files.entities.File;
import com.example.business.files.exceptions.FileNotFoundException;
import com.example.business.files.repositories.FilesRepository;

@Service
public class FilesService {
    private final FilesRepository filesRepository;
    private final String uploadDir = "uploads/";

    public FilesService(FilesRepository filesRepository) {
        this.filesRepository = filesRepository;
    }

    // Upload File
    public FilesUploadResponse uploadFile(FilesUploadRequest request) throws IOException {
        MultipartFile file = request.getFile();
        if (file.isEmpty()) throw new RuntimeException("File is empty");
        String fileName = UUID.randomUUID().toString() + "-" + request.getReferenceType() + "-" + file.getOriginalFilename();
        
        java.io.File directory = new java.io.File(uploadDir);
        if (!directory.exists()) {
            directory.mkdirs();
        }

        java.io.File destination = new java.io.File(uploadDir + fileName);
        file.transferTo(destination);

        File newFile = new File();
        newFile.setFileName(fileName);
        newFile.setFilePath("/uploads/" + fileName);
        newFile.setFileType(file.getContentType());
        newFile.setFileSize(file.getSize());
        newFile.setReferenceId(request.getReferenceId());
        newFile.setReferenceType(request.getReferenceType());

        File saved = filesRepository.save(newFile);
        
        return new FilesUploadResponse(
            saved.getId(),
            saved.getFileName(),
            saved.getFilePath(),
            saved.getFileType(),
            saved.getFileSize(),
            "File uploaded successfully"
        );
    }

    // Update File
    public FilesUpdateResponse updateFile(FilesUpdateRequest request) throws IOException {
        File existing = filesRepository.findById(request.getId())
            .orElseThrow(() -> new FileNotFoundException(request.getId()));

        MultipartFile newFile = request.getFile();
        if (newFile != null && !newFile.isEmpty()) {

            String fileName = UUID.randomUUID().toString() + "-" + newFile.getOriginalFilename();
            java.io.File destination = new java.io.File(uploadDir + fileName);

            newFile.transferTo(destination);

            existing.setFileName(fileName);
            existing.setFilePath("/uploads/" + fileName);
            existing.setFileType(newFile.getContentType());
            existing.setFileSize(newFile.getSize());
        }

        File saved = filesRepository.save(existing);

        return new FilesUpdateResponse(
            saved.getId(),
            saved.getFileName(),
            saved.getFilePath(),
            saved.getFileType(),
            saved.getFileSize(),
            "File updated successfully"
        );
    }

    // Delete File
    public FilesDeleteResponse deleteFile(Long id) {
        File file = filesRepository.findById(id)
            .orElseThrow(() -> new FileNotFoundException(id));

        java.io.File existingFile = new java.io.File(uploadDir + file.getFileName());
        if (existingFile.exists()) {
            existingFile.delete();
        }

        filesRepository.delete(file);

        return new FilesDeleteResponse(
            file.getId(),
            "File deleted successfully"
        );
    }
    
    // Getter

    // Get File By Reference Id
    public FilesByReferenceIdResponse getFilesByReferenceId(FilesByReferenceIdRequest request) {
        File file = filesRepository.findFirstByReferenceIdAndReferenceType(request.getReferenceId(), request.getReferenceType())
            .stream()
            .findFirst()
            .orElseThrow(() -> new FileNotFoundException(request.getReferenceId()));

        return new FilesByReferenceIdResponse(
            file.getId(),
            file.getFileName(),
            file.getFilePath()    
        );
    }

    // Get File By Path
    public FilesByPathResponse getFileByPath(FilesByPathRequest request) {
        File file = filesRepository.findByFilePath(request.getFilePath())
            .orElseThrow(() -> new FileNotFoundException(0L));

        return new FilesByPathResponse(
            file.getId(),
            file.getFileName(),
            file.getFilePath()
        );
    } 

    // Get File By Id
    public FilesDetailResponse getFileById(Long id) {
        File file = filesRepository.findById(id)
            .orElseThrow(() -> new FileNotFoundException(id));

        return new FilesDetailResponse(
            file.getId(),
            file.getFileName(),
            file.getFilePath(),
            file.getFileType(),
            file.getFileSize(),
            file.getReferenceId(),
            file.getReferenceType()
        );
    }
}
