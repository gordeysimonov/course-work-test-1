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
    public ResponseEntity<?> getMusicFile(@RequestParam(value = "id") Long id) {
        return musicFileService.getMusicFileById(id);
    }

    @GetMapping("/music-file/get-music-files")
    public ResponseEntity<?> getMusicFile() {
        return musicFileService.getMusicFiles();
    }

    @PutMapping("/music-file")
    public ResponseEntity<?> updateMusicFile(@RequestBody MusicFile newMusicFileData) {
        return musicFileService.updateMusicFile(newMusicFileData.getId(), newMusicFileData);
    }

    @DeleteMapping("/music-file")
    public ResponseEntity<?> deleteUser(@RequestParam(value = "id") Long id) {
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

}
