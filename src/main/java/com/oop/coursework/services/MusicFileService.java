package com.oop.coursework.services;

import com.oop.coursework.model.Genre;
import com.oop.coursework.model.MusicFile;
import com.oop.coursework.model.Rate;
import com.oop.coursework.model.Tag;
import com.oop.coursework.repo.GenreRepo;
import com.oop.coursework.repo.MusicFileRepo;
import com.oop.coursework.repo.TagRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class MusicFileService {

    private MusicFileRepo musicFileRepository;
    private GenreRepo genreRepository;
    private TagRepo tagRepository;

    @Autowired
    public MusicFileService(MusicFileRepo musicFileRepository, GenreRepo genreRepository, TagRepo tagRepository) {
        this.musicFileRepository = musicFileRepository;
        this.genreRepository = genreRepository;
        this.tagRepository = tagRepository;
    }

    public void createNewMusicFile(MusicFile musicFile) {
        musicFile.setDownloadDate(LocalDateTime.now());
        musicFile.setDownloadsNumber(0);
        musicFileRepository.save(musicFile);
    }

    public ResponseEntity<?> getMusicFileById(Long id) {
        Optional<MusicFile> optionalMusicFile = musicFileRepository.findById(id);
        if (optionalMusicFile.isPresent()) {
            return ResponseEntity.ok(optionalMusicFile.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    public ResponseEntity<?> getMusicFiles() {
        List<MusicFile> musicFiles = musicFileRepository.findAll();
        return ResponseEntity.ok(musicFiles);
    }

    public ResponseEntity<?> updateMusicFile(Long id, MusicFile newMusicFileData) {
        Optional<MusicFile> optionalMusicFile = musicFileRepository.findById(id);
        if (optionalMusicFile.isPresent()) {
            MusicFile existingMusicFile = optionalMusicFile.get();
            existingMusicFile.setName(newMusicFileData.getName());
            existingMusicFile.setAuthor(newMusicFileData.getAuthor());
            existingMusicFile.setYear(newMusicFileData.getYear());
            existingMusicFile.setDescription(newMusicFileData.getDescription());
            existingMusicFile.setLyrics(newMusicFileData.getLyrics());
            existingMusicFile.setFilePath(newMusicFileData.getFilePath());
            existingMusicFile.setFileType(newMusicFileData.getFileType());
            if(newMusicFileData.getDownloadDate() != null) {
                existingMusicFile.setDownloadDate(newMusicFileData.getDownloadDate());
            }
            if(newMusicFileData.getDownloadsNumber() >= 0) {
                existingMusicFile.setDownloadsNumber(newMusicFileData.getDownloadsNumber());
            }
            if(newMusicFileData.getUserId() != null) {
                existingMusicFile.setUserId(newMusicFileData.getUserId());
            }
            if(newMusicFileData.getMusicFileRatingList() != null) {
                existingMusicFile.setMusicFileRatingList(newMusicFileData.getMusicFileRatingList());
            }
            if(newMusicFileData.getMusicFileCommentList() != null) {
                existingMusicFile.setMusicFileCommentList(newMusicFileData.getMusicFileCommentList());
            }
            if(newMusicFileData.getGenres() != null) {
                existingMusicFile.setGenres(newMusicFileData.getGenres());
            }
            if(newMusicFileData.getTags() != null) {
                existingMusicFile.setTags(newMusicFileData.getTags());
            }
            if(newMusicFileData.getCategories() != null) {
                existingMusicFile.setCategories(newMusicFileData.getCategories());
            }
            if(newMusicFileData.getPlaylists() != null) {
                existingMusicFile.setPlaylists(newMusicFileData.getPlaylists());
            }
            musicFileRepository.save(existingMusicFile);
            return ResponseEntity.ok(existingMusicFile);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    public ResponseEntity<?> deleteMusicFile(Long id) {
        Optional<MusicFile> optionalMusicFile = musicFileRepository.findById(id);
        if (optionalMusicFile.isPresent()) {
            musicFileRepository.deleteById(id);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    public MusicFile assignGenreToMusicFile(Long musicFileId, Long genreId) {
        Set<Genre> genreSet;
        MusicFile musicFile = musicFileRepository.findById(musicFileId).get();
        Genre genre = genreRepository.findById(genreId).get();
        genreSet = musicFile.getGenres();
        genreSet.add(genre);
        musicFile.setGenres(genreSet);
        return musicFileRepository.save(musicFile);
    }

    public MusicFile assignTagToMusicFile(Long musicFileId, Long tagId) {
        Set<Tag> tagSet;
        MusicFile musicFile = musicFileRepository.findById(musicFileId).get();
        Tag tag = tagRepository.findById(tagId).get();
        tagSet =  musicFile.getTags();
        tagSet.add(tag);
        musicFile.setTags(tagSet);
        return musicFileRepository.save(musicFile);
    }

}
