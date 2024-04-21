package com.oop.coursework.rest;

import com.oop.coursework.model.Genre;
import com.oop.coursework.services.GenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class GenreController {

    private final GenreService genreService;

    @Autowired
    public GenreController(GenreService genreService) {
        this.genreService = genreService;
    }

    @PostMapping("genre")
    public ResponseEntity<?> createNewGenre(@RequestBody Genre genre){
        return ResponseEntity.ok(genreService.createNewGenre(genre));
    }

    @GetMapping("genre")
    public ResponseEntity<?> getGenreById(@RequestParam(value = "id") long id) {
        return genreService.getGenreById(id);
    }

    @PutMapping("genre")
    public ResponseEntity<?> updateGenre(@RequestBody Genre newGenreData) {
        return genreService.updateGenre(newGenreData.getId(), newGenreData);
    }

    @DeleteMapping("genre")
    public ResponseEntity<?> deleteGenre(@RequestParam(value = "id") long id) {
        return genreService.deleteGenre(id);
    }

}
