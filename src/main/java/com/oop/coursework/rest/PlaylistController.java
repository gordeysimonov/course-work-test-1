package com.oop.coursework.rest;

import com.oop.coursework.model.Playlist;
import com.oop.coursework.services.PlaylistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class PlaylistController {

    private final PlaylistService playlistService;

    @Autowired
    public PlaylistController(PlaylistService playlistService) {
        this.playlistService = playlistService;
    }

    @PostMapping("playlist")
    public ResponseEntity<?> createNewPlaylist(@RequestBody Playlist playlist){
        return ResponseEntity.ok(playlistService.createNewPlaylist(playlist));
    }

    @GetMapping("playlist")
    public ResponseEntity<?> getPlaylistById(@RequestParam(value = "id") long id) {
        return playlistService.getPlaylistById(id);
    }

    @PutMapping("playlist")
    public ResponseEntity<?> updatePlaylist(@RequestBody Playlist newPlaylistData) {
        return playlistService.updatePlaylist(newPlaylistData.getId(), newPlaylistData);
    }

    @DeleteMapping("playlist")
    public ResponseEntity<?> deletePlaylist(@RequestParam(value = "id") long id) {
        return playlistService.deletePlaylist(id);
    }

}
