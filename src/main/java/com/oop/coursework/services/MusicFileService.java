package com.oop.coursework.services;

import com.oop.coursework.model.*;
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

    private final MusicFileRepo musicFileRepository;
    private final GenreRepo genreRepository;
    private final TagRepo tagRepository;
    private final CategoryService categoryService;

    @Autowired
    public MusicFileService(MusicFileRepo musicFileRepository, GenreRepo genreRepository, TagRepo tagRepository, CategoryService categoryService) {
        this.musicFileRepository = musicFileRepository;
        this.genreRepository = genreRepository;
        this.tagRepository = tagRepository;
        this.categoryService = categoryService;
    }

    public void createNewMusicFile(MusicFile musicFile) {
        musicFile.setDownloadDate(LocalDateTime.now());
        musicFile.setDownloadsNumber(0);
        MusicFile savedFile = musicFileRepository.save(musicFile);
        categoryService.updateCategoriesWithFile(savedFile);
    }

    public ResponseEntity<?> getMusicFileById(Long id) {
        List<Object[]> musicFiles = musicFileRepository.findMusicFileById(id);
        return ResponseEntity.ok(musicFiles);
    }

    public ResponseEntity<?> getMusicFiles() {
        List<Object[]> musicFiles = musicFileRepository.findAllMusicFiles();
        return ResponseEntity.ok(musicFiles);
    }

    public ResponseEntity<?> updateMusicFile(Long id, MusicFile newMusicFileData) {
        Optional<MusicFile> optionalMusicFile = musicFileRepository.findById(id);
        if (optionalMusicFile.isPresent()) {
            MusicFile existingMusicFile = optionalMusicFile.get();
            if(newMusicFileData.getName() != null) {
                existingMusicFile.setName(newMusicFileData.getName());
            }
            if(newMusicFileData.getAuthor() != null) {
                existingMusicFile.setAuthor(newMusicFileData.getAuthor());
            }
            if(newMusicFileData.getYear() != 0) {
                existingMusicFile.setYear(newMusicFileData.getYear());
            }
            if(newMusicFileData.getDescription() != null) {
                existingMusicFile.setDescription(newMusicFileData.getDescription());
            }
            if(newMusicFileData.getLyrics() != null) {
                existingMusicFile.setLyrics(newMusicFileData.getLyrics());
            }
            if(newMusicFileData.getFilePath() != null) {
                existingMusicFile.setFilePath(newMusicFileData.getFilePath());
            }
            if(newMusicFileData.getFileType() != null) {
                existingMusicFile.setFileType(newMusicFileData.getFileType());
            }
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

    public ResponseEntity<?> deleteMusicFile(long id) {
        Optional<MusicFile> optionalMusicFile = musicFileRepository.findById(id);
        if (optionalMusicFile.isPresent()) {
            MusicFile musicFile = optionalMusicFile.get();

            for (Genre genre : musicFile.getGenres()) {
                removeGenreFromMusicFiles(genre.getId());
            }
            for (Tag tag : musicFile.getTags()) {
                removeTagFromMusicFiles(tag.getId());
            }

            musicFileRepository.delete(musicFile);
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

    public List<Object[]> getMusicFilesByUserId(Long userId) {
        return musicFileRepository.findMusicFilesByUserId(userId);
    }

    public ResponseEntity<?> findMusicFilesByTags(List<String> tags) {
        int tagCount = tags.size();

        List<Object[]> musicFiles = musicFileRepository.findByTags(tags, tagCount);
        return ResponseEntity.ok(musicFiles);
    }

    public ResponseEntity<?> findMusicFilesByGenres(List<String> genres) {
        int genreCount = genres.size();

        List<Object[]> musicFiles = musicFileRepository.findByGenres(genres, genreCount);
        return ResponseEntity.ok(musicFiles);
    }

    public ResponseEntity<?> findMusicFilesByName(String name) {
        List<Object[]> musicFiles = musicFileRepository.findByName(name);
        return ResponseEntity.ok(musicFiles);
    }

    public ResponseEntity<?> findMusicFilesByAuthor(String author) {
        List<Object[]> musicFiles = musicFileRepository.findByAuthor(author);
        return ResponseEntity.ok(musicFiles);
    }

    public ResponseEntity<?> findMusicFilesByYear(int year) {
        List<Object[]> musicFiles = musicFileRepository.findByYear(year);
        return ResponseEntity.ok(musicFiles);
    }

    public void removeGenreFromMusicFiles(Long genreId) {
        Set<MusicFile> musicFiles = musicFileRepository.findByGenresId(genreId);
        for (MusicFile musicFile : musicFiles) {
            musicFile.getGenres().removeIf(genre -> genre.getId().equals(genreId));
            musicFileRepository.save(musicFile);
        }
    }

    public void removeTagFromMusicFiles(Long tagId) {
        Set<MusicFile> musicFiles = musicFileRepository.findByTagsId(tagId);
        for (MusicFile musicFile : musicFiles) {
            musicFile.getTags().removeIf(tag -> tag.getId().equals(tagId));
            musicFileRepository.save(musicFile);
        }
    }

}
