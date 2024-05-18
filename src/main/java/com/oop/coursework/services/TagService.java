package com.oop.coursework.services;

import com.oop.coursework.annotation.LogService;
import com.oop.coursework.model.Tag;
import com.oop.coursework.repo.TagRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TagService {

    private final TagRepo tagRepository;

    @Autowired
    public TagService(TagRepo tagRepository) {
        this.tagRepository = tagRepository;
    }

    @LogService
    public void createNewTag(Tag tag) {
        tagRepository.save(tag);
    }

    @LogService
    public ResponseEntity<?> getTagById(long id) {
        List<Object[]> tags = tagRepository.findTagById(id);
        return ResponseEntity.ok(tags);
    }

    @LogService
    public ResponseEntity<?> getAllTags() {
        List<Object[]> tags = tagRepository.findAllTags();
        return ResponseEntity.ok(tags);
    }

    @LogService
    public ResponseEntity<?> updateTag(long id, Tag newTagData) {
        Optional<Tag> optionalTag = tagRepository.findById(id);
        if (optionalTag.isPresent()) {
            Tag existingTag = optionalTag.get();
            existingTag.setTagName(newTagData.getTagName());
            tagRepository.save(existingTag);
            return ResponseEntity.ok(existingTag);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @LogService
    public ResponseEntity<?> deleteTag(long id) {
        Optional<Tag> optionalTag = tagRepository.findById(id);
        if (optionalTag.isPresent()) {
            tagRepository.deleteById(id);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
