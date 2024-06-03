package com.rhmaster.rhmaster.repository;

import com.rhmaster.rhmaster.models.WorkSection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface WorkSectionRepositoy extends JpaRepository<WorkSection, UUID> {
}
