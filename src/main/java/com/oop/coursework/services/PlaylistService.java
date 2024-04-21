package com.oop.coursework.services;

import com.oop.coursework.model.Playlist;
import com.oop.coursework.repo.PlaylistRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PlaylistService {

    private PlaylistRepo playlistRepository;

    @Autowired
    public PlaylistService(PlaylistRepo playlistRepository) {
        this.playlistRepository = playlistRepository;
    }

    public Playlist createNewPlaylist(Playlist playlist) {
        return playlistRepository.save(playlist);
    }

    public ResponseEntity<?> getPlaylistById(long id) {
        Optional<Playlist> optionalPlaylist = playlistRepository.findById(id);
        if (optionalPlaylist.isPresent()) {
            return ResponseEntity.ok(optionalPlaylist.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    public ResponseEntity<?> updatePlaylist(long id, Playlist newPlaylistData) {
        Optional<Playlist> optionalPlaylist = playlistRepository.findById(id);
        if (optionalPlaylist.isPresent()) {
            Playlist existingPlaylist = optionalPlaylist.get();
            existingPlaylist.setName(newPlaylistData.getName());
            existingPlaylist.setDescription(newPlaylistData.getDescription());
            if(newPlaylistData.getMusicFiles() != null) {
                existingPlaylist.setMusicFiles(newPlaylistData.getMusicFiles());
            }
            if(newPlaylistData.getUserId() != null) {
                existingPlaylist.setUserId(newPlaylistData.getUserId());
            }
            playlistRepository.save(existingPlaylist);
            return ResponseEntity.ok(existingPlaylist);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    public ResponseEntity<?> deletePlaylist(long id) {
        Optional<Playlist> optionalPlaylist = playlistRepository.findById(id);
        if (optionalPlaylist.isPresent()) {
            playlistRepository.deleteById(id);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
