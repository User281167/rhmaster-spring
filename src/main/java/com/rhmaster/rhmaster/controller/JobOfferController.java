package com.rhmaster.rhmaster.controller;

import com.rhmaster.rhmaster.Services.JobOfferService;
import com.rhmaster.rhmaster.models.JobOffer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/ofertas")
public class JobOfferController {
    @Autowired
    private JobOfferService service;

    public JobOfferController(JobOfferService service) {
        this.service = service;
    }

    @GetMapping("/todos")
    public List<JobOffer> getJobOffer() {
        return service.getJobOffers();
    }

    @GetMapping("/{id}")
    public Optional<JobOffer> getJobOffer(@PathVariable("id") UUID id) {
        return service.getJobOffer(id);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable("id") UUID id) {
        service.deleteById(id);
    }

    @PostMapping
    public void save(@RequestBody JobOffer JobOffer) {
        service.save(JobOffer);
    }

    @PostMapping("/lista")
    public void save(@RequestBody LinkedList<JobOffer> JobOffers) {
        service.save(JobOffers);
    }
}
