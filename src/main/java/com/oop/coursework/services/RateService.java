package com.oop.coursework.services;

import com.oop.coursework.model.Category;
import com.oop.coursework.model.MusicFile;
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

    public void createNewRate(Rate rate) {
        rateRepository.save(rate);
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

    public void updateAverageRate() {
        List<MusicFile> musicFiles = musicFileRepository.findAll();
        for (MusicFile musicFileData : musicFiles) {
            Long fileId = musicFileData.getId();
            MusicFile musicFile = musicFileRepository.findById(fileId).orElse(null);
            if (musicFile != null) {
                musicFile.updateAverageRate();
                musicFileRepository.save(musicFile);

                List<Category> categories = categoryRepository.findAll();
                for (Category category : categories) {
                    categoryService.sortMusicFilesInCategory(category);
                }
            }
        }
    }

}
