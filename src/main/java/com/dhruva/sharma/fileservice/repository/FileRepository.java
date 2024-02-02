package com.dhruva.sharma.fileservice.repository;

import com.dhruva.sharma.fileservice.model.File;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FileRepository extends JpaRepository<File, String> {

}
