package com.oop.coursework.rest;

import com.oop.coursework.annotation.LogController;
import com.oop.coursework.model.Category;
import com.oop.coursework.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class CategoryController {

    private final CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @LogController
    @PostMapping("category")
    public ResponseEntity<?> createNewCategory(@RequestBody Category category){
        categoryService.createNewCategory(category);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @LogController
    @GetMapping("category")
    public ResponseEntity<?> getCategoryById(@RequestParam(value = "id") long id) {
        return categoryService.getCategoryById(id);
    }

    @LogController
    @GetMapping("category/get-categories")
    public ResponseEntity<?> getCategories() {
        return categoryService.getCategories();
    }

    @LogController
    @PutMapping("category")
    public ResponseEntity<?> updateCategory(@RequestBody Category newCategoryData) {
        return categoryService.updateCategory(newCategoryData.getId(), newCategoryData);
    }

    @LogController
    @DeleteMapping("category")
    public ResponseEntity<?> deleteCategory(@RequestParam(value = "id") long id) {
        return categoryService.deleteCategory(id);
    }

    @LogController
    @PutMapping("/category/{categoryId}/musicFile/{musicFileId}")
    public ResponseEntity<?> assignMusicFileToCategory(
            @PathVariable Long musicFileId,
            @PathVariable Long categoryId
    ){
        return categoryService.assignMusicFileToCategory(categoryId, musicFileId);
    }

}
