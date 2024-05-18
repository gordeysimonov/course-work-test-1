package com.oop.coursework.services;

import com.oop.coursework.annotation.LogService;
import com.oop.coursework.model.MusicFile;
import com.oop.coursework.model.Playlist;
import com.oop.coursework.repo.MusicFileRepo;
import com.oop.coursework.repo.PlaylistRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class PlaylistService {

    private final PlaylistRepo playlistRepository;

    private final MusicFileRepo musicFileRepository;

    @Autowired
    public PlaylistService(PlaylistRepo playlistRepository, MusicFileRepo musicFileRepository) {
        this.playlistRepository = playlistRepository;
        this.musicFileRepository = musicFileRepository;
    }

    @LogService
    public void createNewPlaylist(Playlist playlist) {
        playlistRepository.save(playlist);
    }

    @LogService
    public ResponseEntity<?> getPlaylistById(Long id) {
        List<Object[]> playlists = playlistRepository.findPlaylistById(id);
        return ResponseEntity.ok(playlists);
    }

    @LogService
    public ResponseEntity<?> getPlaylists() {
        List<Object[]> playlists = playlistRepository.findAllPlaylists();
        return ResponseEntity.ok(playlists);
    }

    @LogService
    public ResponseEntity<?> updatePlaylist(long id, Playlist newPlaylistData) {
        Optional<Playlist> optionalPlaylist = playlistRepository.findById(id);
        if (optionalPlaylist.isPresent()) {
            Playlist existingPlaylist = optionalPlaylist.get();
            if(newPlaylistData.getName() != null) {
                existingPlaylist.setName(newPlaylistData.getName());
            }
            if(newPlaylistData.getDescription() != null) {
                existingPlaylist.setDescription(newPlaylistData.getDescription());
            }
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

    @LogService
    public ResponseEntity<?> deletePlaylist(long id) {
        Optional<Playlist> optionalPlaylist = playlistRepository.findById(id);
        if (optionalPlaylist.isPresent()) {
            playlistRepository.deleteById(id);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @LogService
    public Playlist assignMusicFileToPlaylist(Long playlistId, Long musicFileId) {
        Set<MusicFile> musicFileSet;
        Playlist playlist = playlistRepository.findById(playlistId).get();
        MusicFile musicFile = musicFileRepository.findById(musicFileId).get();
        musicFileSet = playlist.getMusicFiles();
        musicFileSet.add(musicFile);
        playlist.setMusicFiles(musicFileSet);
        return playlistRepository.save(playlist);
    }

    @LogService
    public ResponseEntity<?> findByNameAndUser(String name, Long id) {
        List<Object[]> playlists = playlistRepository.findByNameAndUser(name, id);
        return ResponseEntity.ok(playlists);
    }

    @LogService
    public ResponseEntity<?> findAllPlaylists(Long id) {
        List<Object[]> playlists = playlistRepository.findAllPlaylists(id);
        return ResponseEntity.ok(playlists);
    }

}
