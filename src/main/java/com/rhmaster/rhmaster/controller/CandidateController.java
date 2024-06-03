package com.rhmaster.rhmaster.controller;

import com.rhmaster.rhmaster.Services.CandidateService;
import com.rhmaster.rhmaster.dtos.CandidateDto;
import com.rhmaster.rhmaster.models.Candidate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/candidatos")
public class CandidateController {
    @Autowired
    private CandidateService candidateService;

    @GetMapping("/oferta/{jobOfferId}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public List<Candidate> getCandidatesByJobOffer(@PathVariable("jobOfferId") UUID jobOfferId) {
        return candidateService.findAllCandidateByJobOfferId(jobOfferId);
    }

    @GetMapping("/candidato/{employeeId}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public List<Candidate> getCandidatesByEmployee(@PathVariable("employeeId") UUID employeeId) {
        return candidateService.findAllCandidateByEmployeeId(employeeId);
    }

    @DeleteMapping("/{candidateId}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void deleteCandidate(@PathVariable("candidateId") UUID candidateId) {
        candidateService.deleteCandidateById(candidateId);
    }

    @PostMapping
    @PreAuthorize("hasRole('ROLE_CANDIDATO')")
    public void saveCandidate(@RequestBody CandidateDto candidateDto) {
        candidateService.saveCandidate(candidateDto);
    }

    @PostMapping("/preliminar/{candidateId}/{value}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void setPreliminary(@PathVariable("candidateId") UUID candidateId, @PathVariable("value") boolean value) {
        candidateService.setPreliminary(candidateId, value);
    }
}
