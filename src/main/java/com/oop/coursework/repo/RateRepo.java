package com.oop.coursework.repo;

import com.oop.coursework.annotation.LogRepository;
import com.oop.coursework.model.Rate;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RateRepo extends JpaRepository<Rate, Long> {

    @LogRepository
    @Query("SELECT json_build_object(" +
            "'id', r.id, " +
            "'rate', r.rate, " +
            "'users', (SELECT json_agg(json_build_object('id', u.id, 'username', u.username)) FROM r.userId u), " +
            "'musicFileId', r.musicFileId.id " +
            ") " +
            "FROM Rate r WHERE r.id = :id")
    List<Object[]> findRateById(@Param("id") Long id);

    @LogRepository
    @Modifying
    @Transactional
    @Query("DELETE FROM Rate r WHERE r.id = :id")
    void deleteRateById(@Param("id") Long id);

}
