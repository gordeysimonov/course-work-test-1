package com.oop.coursework.rest;

import com.oop.coursework.model.Rate;
import com.oop.coursework.services.RateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class RateController {

    private final RateService rateService;

    @Autowired
    public RateController(RateService rateService) {
        this.rateService = rateService;
    }

    @PostMapping("rate")
    public ResponseEntity<?> createNewRate(@RequestBody Rate rate){
        return ResponseEntity.ok(rateService.createNewRate(rate));
    }

    @GetMapping("rate")
    public ResponseEntity<?> getRateById(@RequestParam(value = "id") long id) {
        return rateService.getRateById(id);
    }

    @PutMapping("rate")
    public ResponseEntity<?> updateRate(@RequestBody Rate newRateData) {
        return rateService.updateRate(newRateData.getId(), newRateData);
    }

    @DeleteMapping("rate")
    public ResponseEntity<?> deleteRate(@RequestParam(value = "id") long id) {
        return rateService.deleteRate(id);
    }

}
