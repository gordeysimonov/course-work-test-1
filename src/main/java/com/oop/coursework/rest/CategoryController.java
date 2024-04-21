package com.oop.coursework.rest;

import com.oop.coursework.model.Category;
import com.oop.coursework.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class CategoryController {

    private final CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping("category")
    public ResponseEntity<?> createNewCategory(@RequestBody Category category){
        return ResponseEntity.ok(categoryService.createNewCategory(category));
    }

    @GetMapping("category")
    public ResponseEntity<?> getCategoryById(@RequestParam(value = "id") long id) {
        return categoryService.getCategoryById(id);
    }

    @PutMapping("category")
    public ResponseEntity<?> updateCategory(@RequestBody Category newCategoryData) {
        return categoryService.updateCategory(newCategoryData.getId(), newCategoryData);
    }

    @DeleteMapping("category")
    public ResponseEntity<?> deleteCategory(@RequestParam(value = "id") long id) {
        return categoryService.deleteCategory(id);
    }

}
