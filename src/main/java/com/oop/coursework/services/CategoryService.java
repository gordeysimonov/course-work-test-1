package com.oop.coursework.services;

import com.oop.coursework.annotation.LogService;
import com.oop.coursework.model.Category;
import com.oop.coursework.model.MusicFile;
import com.oop.coursework.repo.CategoryRepo;
import com.oop.coursework.repo.MusicFileRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    private final CategoryRepo categoryRepository;

    private final MusicFileRepo musicFileRepository;

    @Autowired
    public CategoryService(CategoryRepo categoryRepository, MusicFileRepo musicFileRepository) {
        this.categoryRepository = categoryRepository;
        this.musicFileRepository = musicFileRepository;
    }

    @LogService
    public void createNewCategory(Category category) {
        categoryRepository.save(category);
    }

    @LogService
    public ResponseEntity<?> getCategoryById(Long id) {
        List<Object[]> categories = categoryRepository.findCategoryById(id);
        return ResponseEntity.ok(categories);
    }

    @LogService
    public ResponseEntity<?> getCategories() {
        List<Object[]> categories = categoryRepository.findAllCategories();
        return ResponseEntity.ok(categories);
    }

    @LogService
    public ResponseEntity<?> updateCategory(long id, Category newCategoryData) {
        Optional<Category> optionalCategory = categoryRepository.findById(id);
        if (optionalCategory.isPresent()) {
            Category existingCategory = optionalCategory.get();
            if(newCategoryData.getName() != null) {
                existingCategory.setName(newCategoryData.getName());
            }
            if(newCategoryData.getDescription() != null) {
                existingCategory.setDescription(newCategoryData.getDescription());
            }
            if(newCategoryData.getImageUrl() != null) {
                existingCategory.setImageUrl(newCategoryData.getImageUrl());
            }
            if(newCategoryData.getMusicFiles() != null && !newCategoryData.getMusicFiles().isEmpty() ) {
                existingCategory.setMusicFiles(newCategoryData.getMusicFiles());
            }
            categoryRepository.save(existingCategory);
            return ResponseEntity.ok(existingCategory);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @LogService
    public ResponseEntity<?> deleteCategory(long id) {
        Optional<Category> optionalCategory = categoryRepository.findById(id);
        if (optionalCategory.isPresent()) {
            categoryRepository.deleteById(id);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @LogService
    public ResponseEntity<?> assignMusicFileToCategory(Long categoryId, Long musicFileId) {
        List<MusicFile> musicFileSet;
        Category category = categoryRepository.findById(categoryId).get();
        MusicFile musicFile = musicFileRepository.findById(musicFileId).get();
        if (category.getMusicFiles().contains(musicFile)) {
            return ResponseEntity.internalServerError().build();
        }
        musicFileSet = category.getMusicFiles();
        musicFileSet.add(musicFile);
        category.setMusicFiles(musicFileSet);
        return ResponseEntity.ok().body(categoryRepository.save(category));
    }

    @LogService
    public void updateCategoriesWithFile(MusicFile musicFile) {
        List<Category> categories = categoryRepository.findAll();

        for (Category category : categories) {
            if (categoryContainsFile(category, musicFile)) {
                continue;
            }
                category.getMusicFiles().add(musicFile);
                sortMusicFilesInCategory(category);
        }

        categoryRepository.saveAll(categories);
    }

    private boolean categoryContainsFile(Category category, MusicFile musicFile) {
        return category.getMusicFiles().stream().anyMatch(file -> file.getId().equals(musicFile.getId()));
    }

    @LogService
    void sortMusicFilesInCategory(Category category) {
        category.getMusicFiles().sort((file1, file2) -> {
            if (category.getName().equals("Most Downloaded!")) {
                int downloadComparison = Integer.compare(file2.getDownloadsNumber(), file1.getDownloadsNumber());
                if (downloadComparison != 0) {
                    return downloadComparison;
                } else {
                    return Double.compare(file2.getAverageRate(), file1.getAverageRate());
                }
            } else if (category.getName().equals("Most Rated!")) {
                int ratingComparison = Double.compare(file2.getAverageRate(), file1.getAverageRate());
                if (ratingComparison != 0) {
                    return ratingComparison;
                } else {
                    if (file1.getMusicFileRatingList() != null && file2.getMusicFileRatingList() != null) {
                        return Integer.compare(file2.getMusicFileRatingList().size(), file1.getMusicFileRatingList().size());
                    } else {
                        return 0;
                    }
                }
            } else if (category.getName().equals("Most Recent!")) {
                return file2.getDownloadDate().compareTo(file1.getDownloadDate());
            } else {
                return 0;
            }
        });
    }

}
