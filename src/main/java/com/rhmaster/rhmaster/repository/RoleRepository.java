package com.rhmaster.rhmaster.repository;

import com.rhmaster.rhmaster.models.ERole;
import com.rhmaster.rhmaster.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByName(ERole name);
}