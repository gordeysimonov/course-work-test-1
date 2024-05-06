package com.oop.coursework.rest;

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

    @PostMapping("/music-file")
    public ResponseEntity<?> createNewMusicFile(@RequestBody MusicFile musicFile){
        musicFileService.createNewMusicFile(musicFile);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/music-file")
    public ResponseEntity<?> getMusicFileById(@RequestParam(value = "id") Long id) {
        return musicFileService.getMusicFileById(id);
    }

    @GetMapping("/music-file/get-music-files")
    public ResponseEntity<?> getMusicFiles() {
        return musicFileService.getMusicFiles();
    }

    @PutMapping("/music-file")
    public ResponseEntity<?> updateMusicFile(@RequestBody MusicFile newMusicFileData) {
        return musicFileService.updateMusicFile(newMusicFileData.getId(), newMusicFileData);
    }

    @DeleteMapping("/music-file")
    public ResponseEntity<?> deleteMusicFile(@RequestParam(value = "id") long id) {
        return musicFileService.deleteMusicFile(id);
    }

    @PutMapping("/musicFile/{musicFileId}/genre/{genreId}")
    public MusicFile assignGenreToMusicFile(
            @PathVariable Long musicFileId,
            @PathVariable Long genreId
    ){
        return musicFileService.assignGenreToMusicFile(musicFileId, genreId);
    }

    @PutMapping("/musicFile/{musicFileId}/tag/{tagId}")
    public MusicFile assignTagToMusicFile(
            @PathVariable Long musicFileId,
            @PathVariable Long tagId
    ){
        return musicFileService.assignTagToMusicFile(musicFileId, tagId);
    }

    @GetMapping("/music-file/get-by-user")
    public ResponseEntity<List<Object[]>> getMusicFilesByUserId(@RequestParam Long id) {
        List<Object[]> musicFiles = musicFileService.getMusicFilesByUserId(id);
        return ResponseEntity.ok(musicFiles);
    }

    @GetMapping("/music-file/by-tags")
    public ResponseEntity<?> getMusicFilesByTags(@RequestParam List<String> tags) {
        return musicFileService.findMusicFilesByTags(tags);
    }

    @GetMapping("/music-file/by-genres")
    public ResponseEntity<?> getMusicFilesByGenres(@RequestParam List<String> genres) {
        return musicFileService.findMusicFilesByGenres(genres);
    }

    @GetMapping("/music-file/by-name")
    public ResponseEntity<?> getMusicFilesByName(@RequestParam String name) {
        return musicFileService.findMusicFilesByName(name);
    }

    @GetMapping("/music-file/by-author")
    public ResponseEntity<?> getMusicFilesByAuthor(@RequestParam String author) {
        return musicFileService.findMusicFilesByAuthor(author);
    }

    @GetMapping("/music-file/by-year")
    public ResponseEntity<?> getMusicFilesByYear(@RequestParam int year) {
        return musicFileService.findMusicFilesByYear(year);
    }

}
