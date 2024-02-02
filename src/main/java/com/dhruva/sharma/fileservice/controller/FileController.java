package com.dhruva.sharma.fileservice.controller;

import com.dhruva.sharma.fileservice.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.Map;

@RestController()
@RequestMapping("/api/v1/files")
public class FileController {

    @Autowired
    private FileService fileService;

    @PostMapping("/upload")
    public Map<String, Object> uploadFile(@RequestParam("file") MultipartFile file){
        String fileId = fileService.uploadFile(file);
        Map<String, Object> responseMap = new HashMap<>();
        responseMap.put("fileId", fileId);
        return responseMap;
    }

    @GetMapping("/{fileId}")
    public ResponseEntity<Resource> getFile(@PathVariable String fileId){
        Resource resource = fileService.getFile(fileId);
        return ResponseEntity.ok(resource);
    }

    @DeleteMapping("/{fileId}")
    public void deleteFile(@PathVariable String fileId){
        fileService.deleteFile(fileId);
    }
}
