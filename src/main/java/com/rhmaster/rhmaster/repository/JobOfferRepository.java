package com.rhmaster.rhmaster.repository;

import com.rhmaster.rhmaster.models.JobOffer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface JobOfferRepository extends JpaRepository<JobOffer, UUID> {
}
