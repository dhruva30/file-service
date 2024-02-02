package com.dhruva.sharma.fileservice.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "file_details")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class File {
    @Id
    private String fileId;

    private String fileType;

    private String fileName;

    private LocalDateTime createdAt;
}
