package com.oop.coursework.repo;

import com.oop.coursework.model.Rate;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface RateRepo extends JpaRepository<Rate, Long> {

    @Modifying
    @Transactional
    @Query("DELETE FROM Rate r WHERE r.id = :id")
    void deleteRateById(@Param("id") Long id);

}
