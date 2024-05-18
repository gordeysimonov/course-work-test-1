package com.oop.coursework.services;

import com.oop.coursework.annotation.LogService;
import com.oop.coursework.model.Genre;
import com.oop.coursework.model.MusicFile;
import com.oop.coursework.repo.GenreRepo;
import com.oop.coursework.repo.MusicFileRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class GenreService {

    private final GenreRepo genreRepository;
    private final MusicFileRepo musicFileRepository;

    @Autowired
    public GenreService(GenreRepo genreRepository, MusicFileRepo musicFileRepository) {
        this.genreRepository = genreRepository;
        this.musicFileRepository = musicFileRepository;
    }

    @LogService
    public void createNewGenre(Genre genre) {
        genreRepository.save(genre);
    }

    @LogService
    public ResponseEntity<?> getGenreById(long id) {
        List<Object[]> genres = genreRepository.findGenreById(id);
        return ResponseEntity.ok(genres);
    }

    @LogService
    public ResponseEntity<?> getAllGenres() {
        List<Object[]> genres = genreRepository.findAllGenres();
        return ResponseEntity.ok(genres);
    }

    @LogService
    public ResponseEntity<?> updateGenre(long id, Genre newGenreData) {
        Optional<Genre> optionalGenre = genreRepository.findById(id);
        if (optionalGenre.isPresent()) {
            Genre existingGenre = optionalGenre.get();
            if(newGenreData.getGenre() != null) {
                existingGenre.setGenre(newGenreData.getGenre());
            }
            if(newGenreData.getDescription() != null) {
                existingGenre.setDescription(newGenreData.getDescription());
            }
            genreRepository.save(existingGenre);
            return ResponseEntity.ok(existingGenre);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @LogService
    @Transactional
    public ResponseEntity<?> deleteGenre(long id) {
        Optional<Genre> optionalGenre = genreRepository.findById(id);
        if (optionalGenre.isPresent()) {
            Genre genre = optionalGenre.get();

            List<MusicFile> musicFiles = musicFileRepository.findByGenresContains(genre);

            for (MusicFile musicFile : musicFiles) {
                musicFile.getGenres().remove(genre);
                musicFileRepository.save(musicFile);
            }

            genreRepository.deleteById(id);

            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }


}
