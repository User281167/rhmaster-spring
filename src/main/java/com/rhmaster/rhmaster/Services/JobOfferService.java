package com.rhmaster.rhmaster.Services;

import com.rhmaster.rhmaster.models.JobOffer;
import com.rhmaster.rhmaster.repository.JobOfferRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class JobOfferService {
    @Autowired
    JobOfferRepository jobOfferRepository;

    public List<JobOffer> getJobOffers() {
        return jobOfferRepository.findAll();
    }

    public Optional<JobOffer> getJobOffer(UUID id) {
        return jobOfferRepository.findById(id);
    }

    public void save(JobOffer offer) {
        jobOfferRepository.save(offer);
    }

    public void save(LinkedList<JobOffer> offer) {
        offer.forEach(o -> jobOfferRepository.save(o));
    }

    public void deleteById(UUID id) {
        jobOfferRepository.deleteById(id);
    }
}
