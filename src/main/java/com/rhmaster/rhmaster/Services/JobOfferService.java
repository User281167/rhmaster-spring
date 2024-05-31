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
    JobOfferRepository ofertaRepository;

    public List<JobOffer> getJobOffers() {
        return ofertaRepository.findAll();
    }

    public Optional<JobOffer> getJobOffer(UUID id) {
        return ofertaRepository.findById(id);
    }

    public void save(JobOffer offer) {
        ofertaRepository.save(offer);
    }

    public void save(LinkedList<JobOffer> offer) {
        offer.forEach(o -> ofertaRepository.save(o));
    }

    public void deleteById(UUID id) {
        ofertaRepository.deleteById(id);
    }
}
