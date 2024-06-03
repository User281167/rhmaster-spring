package com.rhmaster.rhmaster.repository;

import com.rhmaster.rhmaster.models.PsychoTest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PsychoTestRepository extends JpaRepository<PsychoTest, UUID> {
}
