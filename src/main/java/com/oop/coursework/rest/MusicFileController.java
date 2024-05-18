package com.oop.coursework.rest;

import com.oop.coursework.annotation.LogController;
import com.oop.coursework.model.MusicFile;
import com.oop.coursework.services.MusicFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MusicFileController {

    private final MusicFileService musicFileService;

    @Autowired
    public MusicFileController(MusicFileService musicFileService) {
        this.musicFileService = musicFileService;
    }

    @LogController
    @PostMapping("/music-file")
    public ResponseEntity<?> createNewMusicFile(@RequestBody MusicFile musicFile){
        musicFileService.createNewMusicFile(musicFile);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @LogController
    @GetMapping("/music-file")
    public ResponseEntity<?> getMusicFileById(@RequestParam(value = "id") Long id) {
        return musicFileService.getMusicFileById(id);
    }

    @LogController
    @GetMapping("/music-file/get-music-files")
    public ResponseEntity<?> getMusicFiles() {
        return musicFileService.getMusicFiles();
    }

    @LogController
    @PutMapping("/music-file")
    public ResponseEntity<?> updateMusicFile(@RequestBody MusicFile newMusicFileData) {
        return musicFileService.updateMusicFile(newMusicFileData.getId(), newMusicFileData);
    }

    @LogController
    @DeleteMapping("/music-file")
    public ResponseEntity<?> deleteMusicFile(@RequestParam(value = "id") long id) {
        return musicFileService.deleteMusicFile(id);
    }

    @LogController
    @PutMapping("/musicFile/{musicFileId}/genre/{genreId}")
    public MusicFile assignGenreToMusicFile(
            @PathVariable Long musicFileId,
            @PathVariable Long genreId
    ){
        return musicFileService.assignGenreToMusicFile(musicFileId, genreId);
    }

    @LogController
    @PutMapping("/musicFile/{musicFileId}/tag/{tagId}")
    public MusicFile assignTagToMusicFile(
            @PathVariable Long musicFileId,
            @PathVariable Long tagId
    ){
        return musicFileService.assignTagToMusicFile(musicFileId, tagId);
    }

    @LogController
    @GetMapping("/music-file/get-by-user")
    public ResponseEntity<List<Object[]>> getMusicFilesByUserId(@RequestParam Long id) {
        List<Object[]> musicFiles = musicFileService.getMusicFilesByUserId(id);
        return ResponseEntity.ok(musicFiles);
    }

    @LogController
    @GetMapping("/music-file/by-tags")
    public ResponseEntity<?> getMusicFilesByTags(@RequestParam List<String> tags) {
        return musicFileService.findMusicFilesByTags(tags);
    }

    @LogController
    @GetMapping("/music-file/by-genres")
    public ResponseEntity<?> getMusicFilesByGenres(@RequestParam List<String> genres) {
        return musicFileService.findMusicFilesByGenres(genres);
    }

    @LogController
    @GetMapping("/music-file/by-name")
    public ResponseEntity<?> getMusicFilesByName(@RequestParam String name) {
        return musicFileService.findMusicFilesByName(name);
    }

    @LogController
    @GetMapping("/music-file/by-author")
    public ResponseEntity<?> getMusicFilesByAuthor(@RequestParam String author) {
        return musicFileService.findMusicFilesByAuthor(author);
    }

    @LogController
    @GetMapping("/music-file/by-year")
    public ResponseEntity<?> getMusicFilesByYear(@RequestParam int year) {
        return musicFileService.findMusicFilesByYear(year);
    }

}
