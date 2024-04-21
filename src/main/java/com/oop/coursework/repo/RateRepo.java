package com.oop.coursework.repo;

import com.oop.coursework.model.Rate;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RateRepo extends JpaRepository<Rate, Long> {
}
