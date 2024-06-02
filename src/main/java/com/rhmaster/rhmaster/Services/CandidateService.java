package com.rhmaster.rhmaster.Services;

import com.rhmaster.rhmaster.dtos.CandidateDto;
import com.rhmaster.rhmaster.models.Candidate;
import com.rhmaster.rhmaster.models.Employee;
import com.rhmaster.rhmaster.models.JobOffer;
import com.rhmaster.rhmaster.models.WorkSection;
import com.rhmaster.rhmaster.repository.CandidateRepository;
import com.rhmaster.rhmaster.repository.EmployeeRepository;
import com.rhmaster.rhmaster.repository.JobOfferRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLOutput;
import java.util.List;
import java.util.UUID;

@Service
public class CandidateService {
    @Autowired
    CandidateRepository candidateRepository;

    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    JobOfferRepository jobOfferRepository;

    public List<Candidate> findAllCandidateByJobOfferId(UUID employeeId) {
        return candidateRepository.findAllCandidateByJobOfferId(employeeId);
    }

    public List<Candidate> findAllCandidateByEmployeeId(UUID employeeId) {
        return candidateRepository.findAllCandidateByEmployeeId(employeeId);
    }

    public void saveCandidate(CandidateDto candidateDto) {
        JobOffer offer = jobOfferRepository.getReferenceById(candidateDto.getJobOfferId());
        Employee employee = employeeRepository.getReferenceById(candidateDto.getEmployeeId());

        if (employee == null) {
            System.out.println("Employee not found");
            System.exit(1);
        }

        if (offer == null) {
            System.out.println("Job offer not found");
            System.exit(1);
        }

        Candidate candidate = Candidate.builder()
                .jobOffer(offer)
                .employee(employee)
                .build();

        if (candidate.getId() == null) {
            candidate.setId(UUID.randomUUID());
        }

        candidateRepository.save(candidate);
    }

    public void deleteCandidateById(UUID candidateId) {
        candidateRepository.deleteById(candidateId);
    }

    public void setPreliminary(UUID candidateId, boolean value) {
        Candidate candidate = candidateRepository.getReferenceById(candidateId);
        candidate.setPreliminary(value);
        candidateRepository.save(candidate);
    }
}
