package com.rhmaster.rhmaster.repository;

import com.rhmaster.rhmaster.models.ExtraHour;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ExtraHourRepository extends JpaRepository<ExtraHour, UUID> {
    List<ExtraHour> findAllByScheduleId(UUID scheduleId);
}
