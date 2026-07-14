package com.example.business.files.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.business.files.entities.File;
import com.example.business.files.enums.ReferenceType;

public interface FilesRepository extends JpaRepository<File, Long> {
    Optional<File> findByFilePath(String filePath);
    List<File> findFirstByReferenceIdAndReferenceType(Long referenceId, ReferenceType referenceType);
}
