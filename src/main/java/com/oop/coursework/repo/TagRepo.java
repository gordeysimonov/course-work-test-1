package com.oop.coursework.repo;

import com.oop.coursework.annotation.LogRepository;
import com.oop.coursework.model.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TagRepo extends JpaRepository<Tag, Long> {

    @LogRepository
    @Query("SELECT json_build_object(" +
            "'id', t.id, " +
            "'tagName', t.tagName, " +
            "'musicFiles', (SELECT json_agg(json_build_object('id', mf.id, 'musicFileName', mf.name)) FROM t.musicFiles mf) " +
            ") " +
            "FROM Tag t")
    List<Object[]> findAllTags();

    @LogRepository
    @Query("SELECT json_build_object(" +
            "'id', t.id, " +
            "'tagName', t.tagName, " +
            "'musicFiles', (SELECT json_agg(json_build_object('id', mf.id, 'musicFileName', mf.name)) FROM t.musicFiles mf) " +
            ") " +
            "FROM Tag t WHERE t.id = :id")
    List<Object[]> findTagById(@Param("id") Long id);

}
