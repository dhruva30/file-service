package com.dhruva.sharma.fileservice.exception;

public class FileNotFoundException extends RuntimeException{
    public FileNotFoundException() {
    }

    public FileNotFoundException(String message) {
        super(message);
    }
}
