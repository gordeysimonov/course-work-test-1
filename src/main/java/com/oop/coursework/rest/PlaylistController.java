package com.oop.coursework.rest;

import com.oop.coursework.model.Playlist;
import com.oop.coursework.services.PlaylistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
        playlistService.createNewPlaylist(playlist);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("playlist")
    public ResponseEntity<?> getPlaylistById(@RequestParam(value = "id") long id) {
        return playlistService.getPlaylistById(id);
    }

    @GetMapping("playlist/get-playlists")
    public ResponseEntity<?> getPlaylists() {
        return playlistService.getPlaylists();
    }

    @PutMapping("playlist")
    public ResponseEntity<?> updatePlaylist(@RequestBody Playlist newPlaylistData) {
        return playlistService.updatePlaylist(newPlaylistData.getId(), newPlaylistData);
    }

    @DeleteMapping("playlist")
    public ResponseEntity<?> deletePlaylist(@RequestParam(value = "id") long id) {
        return playlistService.deletePlaylist(id);
    }

    @PutMapping("/playlist/{playlistId}/musicFile/{musicFileId}")
    public Playlist assignMusicFileToPlaylist(
            @PathVariable Long musicFileId,
            @PathVariable Long playlistId
    ){
        return playlistService.assignMusicFileToPlaylist(playlistId, musicFileId);
    }

    @GetMapping("/playlist/by-name")
    public ResponseEntity<?> getPlaylistsByNameAndUser(@RequestParam String name, @RequestParam Long id) {
        return playlistService.findByNameAndUser(name, id);
    }

    @GetMapping("/playlist/get-playlist")
    public ResponseEntity<?> getPlaylists(@RequestParam Long id) {
        return playlistService.findAllPlaylists(id);
    }

}
