package com.dhruva.sharma.fileservice.service;

import com.dhruva.sharma.fileservice.exception.FileNotFoundException;
import com.dhruva.sharma.fileservice.model.File;
import com.dhruva.sharma.fileservice.repository.FileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
public class FileService {

    @Autowired
    private FileRepository fileRepository;

    private final Path uploadPath = Paths.get("uploaded_files");

    public String uploadFile(MultipartFile file){
        File fileEntity = new File();
        String fileId = UUID.randomUUID().toString();
        fileEntity.setFileName(file.getOriginalFilename());
        fileEntity.setFileType(file.getContentType());
        fileEntity.setFileId(fileId);
        fileEntity.setCreatedAt(LocalDateTime.now());
        fileRepository.save(fileEntity);
        try {
            Files.copy(file.getInputStream(), uploadPath.resolve(file.getOriginalFilename()));
        } catch (IOException e) {
            throw new RuntimeException("Exception Occurred when uploading file", e);
        }
        return fileId;
    }

    public Resource getFile(String fileId) {
        Optional<File> file = fileRepository.findById(fileId);
        if(file.isEmpty()){
            throw new FileNotFoundException("File Doesnt Exist");
        }
        File fileDetails = file.get();
        Path fileContent = uploadPath.resolve(fileDetails.getFileName());
        try {
            return new UrlResource(fileContent.toUri());
        } catch (MalformedURLException e) {
            throw new RuntimeException("Invalid File Url", e);
        }
    }

    public void deleteFile(String fileId){
        Optional<File> file = fileRepository.findById(fileId);
        if(file.isEmpty()){
            throw new FileNotFoundException("File Doesnt Exist");
        }
        File fileDetails = file.get();
        try {
            Files.delete(uploadPath.resolve(fileDetails.getFileName()));
        } catch (IOException e) {
            throw new RuntimeException("Unable to delete file", e);
        }
    }

}
