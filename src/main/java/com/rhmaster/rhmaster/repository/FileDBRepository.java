package com.rhmaster.rhmaster.repository;

import com.rhmaster.rhmaster.models.FileDB;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface FileDBRepository extends JpaRepository<FileDB, UUID> {
}
