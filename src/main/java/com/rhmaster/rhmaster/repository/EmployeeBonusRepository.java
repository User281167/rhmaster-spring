package com.rhmaster.rhmaster.repository;

import com.rhmaster.rhmaster.models.EmployeeBonus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface EmployeeBonusRepository extends JpaRepository<EmployeeBonus, UUID> {
    List<EmployeeBonus> findAllByEmployeeId(UUID employeeId);
}
