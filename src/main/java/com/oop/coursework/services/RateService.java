package com.oop.coursework.services;

import com.oop.coursework.annotation.LogService;
import com.oop.coursework.model.Rate;
import com.oop.coursework.repo.CategoryRepo;
import com.oop.coursework.repo.MusicFileRepo;
import com.oop.coursework.repo.RateRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RateService {

    private final RateRepo rateRepository;
    private final MusicFileRepo musicFileRepository;
    private final CategoryRepo categoryRepository;
    private final CategoryService categoryService;

    @Autowired
    public RateService(RateRepo rateRepository, MusicFileRepo musicFileRepository, CategoryRepo categoryRepository, CategoryService categoryService) {
        this.rateRepository = rateRepository;
        this.musicFileRepository = musicFileRepository;
        this.categoryRepository = categoryRepository;
        this.categoryService = categoryService;
    }

    @LogService
    public void createNewRate(Rate rate) {
        rateRepository.save(rate);
    }

    @LogService
    public ResponseEntity<?> getRateById(long id) {
        List<Object[]> rates = rateRepository.findRateById(id);
        return ResponseEntity.ok(rates);
    }

    @LogService
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

    @LogService
    public ResponseEntity<?> deleteRate(long id) {
        Optional<Rate> optionalRate = rateRepository.findById(id);
        if (optionalRate.isPresent()) {
            Rate rate = optionalRate.get();
            rate.getMusicFileId().removeRate(rate);
            rateRepository.deleteRateById(rate.getId());
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
