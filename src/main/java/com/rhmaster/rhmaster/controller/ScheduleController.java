package com.rhmaster.rhmaster.controller;

import com.rhmaster.rhmaster.Services.ScheduleService;
import com.rhmaster.rhmaster.dtos.ScheduleRequestDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/horarios")
public class ScheduleController {
    @Autowired
    private ScheduleService scheduleService;

    @PostMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void saveSchedule(@RequestBody ScheduleRequestDto schedule) {
        scheduleService.saveSchedule(schedule);
    }

    @GetMapping("/{userId}")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_CONTRATADO')")
    public ScheduleRequestDto getSchedule(@PathVariable UUID userId) {
        return scheduleService.getSchedule(userId);
    }
}
