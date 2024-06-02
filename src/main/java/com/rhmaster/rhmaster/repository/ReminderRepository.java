package com.rhmaster.rhmaster.repository;

import com.rhmaster.rhmaster.models.Reminder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ReminderRepository extends JpaRepository<Reminder, UUID> {
    List<Reminder> findAllByUserIdAndType(UUID userId, String type);

    List<Reminder> findAllByUserId(UUID id);
}
