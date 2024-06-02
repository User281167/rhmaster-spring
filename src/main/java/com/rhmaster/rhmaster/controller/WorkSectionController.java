package com.rhmaster.rhmaster.controller;

import com.rhmaster.rhmaster.Services.WorkSectionService;
import com.rhmaster.rhmaster.models.WorkSection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/secciones")
public class WorkSectionController {
    @Autowired
    private WorkSectionService workSectionService;

    @GetMapping("/todos")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public List<WorkSection> getWorkSections() {
        return workSectionService.getWorkSections();
    }

    @PostMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void save(@RequestBody WorkSection workSection) {
        workSectionService.save(workSection);
    }

    @DeleteMapping("/borrar/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void deleteById(@PathVariable("id") UUID id) {
        workSectionService.deleteById(id);
    }
}
