package com.rhmaster.rhmaster.repository;

import com.rhmaster.rhmaster.models.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ScheduleRepository extends JpaRepository<Schedule, UUID> {
    public Schedule findByEmployeeId(UUID employeeId);
}
