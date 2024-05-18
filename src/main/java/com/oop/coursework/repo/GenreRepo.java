package com.oop.coursework.repo;

import com.oop.coursework.annotation.LogRepository;
import com.oop.coursework.model.Genre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface GenreRepo extends JpaRepository<Genre, Long> {

    @LogRepository
    @Query("SELECT json_build_object(" +
            "'id', g.id, " +
            "'genre', g.genre, " +
            "'description', g.description, " +
            "'musicFiles', (SELECT json_agg(json_build_object('id', mf.id, 'musicFileName', mf.name)) FROM g.musicFiles mf) " +
            ") " +
            "FROM Genre g")
    List<Object[]> findAllGenres();

    @LogRepository
    @Query("SELECT json_build_object(" +
            "'id', g.id, " +
            "'genre', g.genre, " +
            "'description', g.description, " +
            "'musicFiles', (SELECT json_agg(json_build_object('id', mf.id, 'musicFileName', mf.name)) FROM g.musicFiles mf) " +
            ") " +
            "FROM Genre g WHERE g.id = :id")
    List<Object[]> findGenreById(@Param("id") Long id);

}
