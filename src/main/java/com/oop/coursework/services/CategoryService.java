package com.oop.coursework.services;

import com.oop.coursework.model.Category;
import com.oop.coursework.repo.CategoryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class CategoryService {

    private CategoryRepo categoryRepository;

    @Autowired
    public CategoryService(CategoryRepo categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public Category createNewCategory(Category category) {
        return categoryRepository.save(category);
    }

    public ResponseEntity<?> getCategoryById(long id) {
        Optional<Category> optionalCategory = categoryRepository.findById(id);
        if (optionalCategory.isPresent()) {
            return ResponseEntity.ok(optionalCategory.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    public ResponseEntity<?> updateCategory(long id, Category newCategoryData) {
        Optional<Category> optionalCategory = categoryRepository.findById(id);
        if (optionalCategory.isPresent()) {
            Category existingCategory = optionalCategory.get();
            existingCategory.setName(newCategoryData.getName());
            existingCategory.setDescription(newCategoryData.getDescription());
            existingCategory.setImageUrl(newCategoryData.getImageUrl());
            categoryRepository.save(existingCategory);
            return ResponseEntity.ok(existingCategory);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    public ResponseEntity<?> deleteCategory(long id) {
        Optional<Category> optionalCategory = categoryRepository.findById(id);
        if (optionalCategory.isPresent()) {
            categoryRepository.deleteById(id);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
