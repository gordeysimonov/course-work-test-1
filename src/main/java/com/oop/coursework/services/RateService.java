package com.oop.coursework.services;

import com.oop.coursework.model.Rate;
import com.oop.coursework.repo.RateRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RateService {

    private RateRepo rateRepository;

    @Autowired
    public RateService(RateRepo rateRepository) {
        this.rateRepository = rateRepository;
    }

    public Rate createNewRate(Rate rate) {
        return rateRepository.save(rate);
    }

    public ResponseEntity<?> getRateById(long id) {
        Optional<Rate> optionalRate = rateRepository.findById(id);
        if (optionalRate.isPresent()) {
            return ResponseEntity.ok(optionalRate.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    public ResponseEntity<?> updateRate(long id, Rate newRateData) {
        Optional<Rate> optionalRate = rateRepository.findById(id);
        if (optionalRate.isPresent()) {
            Rate existingRate = optionalRate.get();
            existingRate.setRate(newRateData.getRate());
            if(newRateData.getUserId() != null) {
                existingRate.setUserId(newRateData.getUserId());
            }
            if(newRateData.getMusicFileId() != null) {
                existingRate.setMusicFileId(newRateData.getMusicFileId());
            }
            rateRepository.save(existingRate);
            return ResponseEntity.ok(existingRate);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    public ResponseEntity<?> deleteRate(long id) {
        Optional<Rate> optionalRate = rateRepository.findById(id);
        if (optionalRate.isPresent()) {
            rateRepository.deleteById(id);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
