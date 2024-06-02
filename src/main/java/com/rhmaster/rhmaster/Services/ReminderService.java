package com.rhmaster.rhmaster.Services;

import com.rhmaster.rhmaster.models.Reminder;
import com.rhmaster.rhmaster.repository.ReminderRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


@Service
public class ReminderService {
    ReminderRepository reminderRepository;

    public List<Reminder> getReminders(UUID userId) {
        return reminderRepository.findAllByUserId(userId);
    }

    public List<Reminder> getReminderByUserIdAndType(UUID userId, String type) {
        return reminderRepository.findAllByUserIdAndType(userId, type.toLowerCase());
    }

    public void save(Reminder reminder) {
        reminderRepository.save(reminder);
    }

    public void deleteById(UUID id) {
        reminderRepository.deleteById(id);
    }
}
