package com.oop.coursework.services;

import com.oop.coursework.model.Genre;
import com.oop.coursework.repo.GenreRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class GenreService {

    private GenreRepo genreRepository;

    @Autowired
    public GenreService(GenreRepo genreRepository) {
        this.genreRepository = genreRepository;
    }

    public Genre createNewGenre(Genre genre) {
        return genreRepository.save(genre);
    }

    public ResponseEntity<?> getGenreById(long id) {
        Optional<Genre> optionalGenre = genreRepository.findById(id);
        if (optionalGenre.isPresent()) {
            return ResponseEntity.ok(optionalGenre.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    public ResponseEntity<?> updateGenre(long id, Genre newGenreData) {
        Optional<Genre> optionalGenre = genreRepository.findById(id);
        if (optionalGenre.isPresent()) {
            Genre existingGenre = optionalGenre.get();
            existingGenre.setGenre(newGenreData.getGenre());
            existingGenre.setDescription(newGenreData.getDescription());
            genreRepository.save(existingGenre);
            return ResponseEntity.ok(existingGenre);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    public ResponseEntity<?> deleteGenre(long id) {
        Optional<Genre> optionalGenre = genreRepository.findById(id);
        if (optionalGenre.isPresent()) {
            genreRepository.deleteById(id);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
