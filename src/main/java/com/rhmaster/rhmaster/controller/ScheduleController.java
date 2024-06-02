package com.rhmaster.rhmaster.controller;

import com.rhmaster.rhmaster.Services.ScheduleMissingService;
import com.rhmaster.rhmaster.Services.ScheduleService;
import com.rhmaster.rhmaster.dtos.ScheduleDto;
import com.rhmaster.rhmaster.dtos.ScheduleMissingDto;
import com.rhmaster.rhmaster.models.ScheduleMissing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/horarios")
public class ScheduleController {
    @Autowired
    private ScheduleService scheduleService;

    @Autowired
    private ScheduleMissingService missingService;

    @PostMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void saveSchedule(@RequestBody ScheduleDto schedule) {
        scheduleService.saveSchedule(schedule);
    }

    @GetMapping("/{userId}")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_CONTRATADO')")
    public ScheduleDto getSchedule(@PathVariable UUID userId) {
        return scheduleService.getSchedule(userId);
    }

    @PostMapping("/falta")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void addMissing(@RequestBody ScheduleMissingDto schedule) {
        missingService.addMissing(schedule);
    }

    @GetMapping("/falta/{scheduleId}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public List<ScheduleMissingDto> saveMissing(@PathVariable("scheduleId") UUID scheduleId) {
        return missingService.getMissing(scheduleId);
    }

    @DeleteMapping("/falta/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void deleteMissing(@PathVariable("id") UUID id) {
        missingService.delete(id);
    }
}
