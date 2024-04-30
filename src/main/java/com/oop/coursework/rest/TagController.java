package com.oop.coursework.rest;

import com.oop.coursework.model.Tag;
import com.oop.coursework.services.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class TagController {

    private final TagService tagService;

    @Autowired
    public TagController(TagService tagService) {
        this.tagService = tagService;
    }

    @PostMapping("tag")
    public ResponseEntity<?> createNewTag(@RequestBody Tag tag){
        tagService.createNewTag(tag);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("tag/get-tags")
    public ResponseEntity<?> getGenres() {
        return tagService.getAllTags();
    }

    @GetMapping("tag")
    public ResponseEntity<?> getTagById(@RequestParam(value = "id") long id) {
        return tagService.getTagById(id);
    }

    @PutMapping("tag")
    public ResponseEntity<?> updateTag(@RequestBody Tag newTagData) {
        return tagService.updateTag(newTagData.getId(), newTagData);
    }

    @DeleteMapping("tag")
    public ResponseEntity<?> deleteTag(@RequestParam(value = "id") long id) {
        return tagService.deleteTag(id);
    }

}
