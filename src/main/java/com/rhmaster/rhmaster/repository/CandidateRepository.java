package com.rhmaster.rhmaster.repository;

import com.rhmaster.rhmaster.models.Candidate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface CandidateRepository extends JpaRepository<Candidate, UUID> {
    public List<Candidate> findAllCandidateByEmployeeId(UUID employeeId);

    public List<Candidate> findAllCandidateByJobOfferId(UUID employeeId);// delete by employee id and job offer id
}
