package com.rhmaster.rhmaster.controller;

import com.rhmaster.rhmaster.Services.ReminderService;
import com.rhmaster.rhmaster.models.Reminder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/recordatorios")
public class ReminderController {
    @Autowired
    private ReminderService reminderService;

    @GetMapping("/todos/{userId}")
    @PreAuthorize("hasRole('ROLE_CONTRATADO') or hasRole('ROLE_PSICO') or hasRole('ROLE_ADMIN')")
    public List<Reminder> getReminders(@PathVariable("userId") UUID userId) {
        return reminderService.getReminders(userId);
    }

    @GetMapping("/type/{userId}/{type}")
    @PreAuthorize("hasRole('ROLE_CONTRATADO') or hasRole('ROLE_PSICO') or hasRole('ROLE_ADMIN')")
    public List<Reminder> getReminderByType(@PathVariable("userId") UUID userId, @PathVariable("type") String type) {
        return reminderService.getReminderByUserIdAndType(userId, type);
    }

    @PostMapping
    @PreAuthorize("hasRole('ROLE_CONTRATADO') or hasRole('ROLE_PSICO') or hasRole('ROLE_ADMIN')")
    public void save(@RequestBody Reminder reminder) {
        reminderService.save(reminder);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_CONTRATADO') or hasRole('ROLE_PSICO') or hasRole('ROLE_ADMIN')")
    public void deleteById(@PathVariable("id") UUID id) {
        reminderService.deleteById(id);
    }
}
