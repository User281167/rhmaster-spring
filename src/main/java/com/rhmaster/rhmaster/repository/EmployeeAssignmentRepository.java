package com.rhmaster.rhmaster.repository;

import com.rhmaster.rhmaster.models.EmployeeAssignment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface EmployeeAssignmentRepository extends JpaRepository<EmployeeAssignment, UUID> {
    List<EmployeeAssignment> findAllByEmployeeId(UUID employeeId);
}
