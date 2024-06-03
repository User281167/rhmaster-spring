package com.rhmaster.rhmaster.repository;

import com.rhmaster.rhmaster.models.ScheduleMissing;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ScheduleMissingRepository extends JpaRepository<ScheduleMissing, UUID> {
    List<ScheduleMissing> findAllByScheduleId(UUID scheduleId);
}
