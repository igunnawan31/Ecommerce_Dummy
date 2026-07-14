package com.example.business.files.exceptions;

import com.example.business.common.exceptions.ResourceNotFoundException;

public class FileNotFoundException extends ResourceNotFoundException {
    public FileNotFoundException(Long id) {
        super("File not found with id: " + id);
    }
}
