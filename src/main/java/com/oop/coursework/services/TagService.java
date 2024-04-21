package com.oop.coursework.services;

import com.oop.coursework.model.Tag;
import com.oop.coursework.repo.TagRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TagService {

    private TagRepo tagRepository;

    @Autowired
    public TagService(TagRepo tagRepository) {
        this.tagRepository = tagRepository;
    }

    public Tag createNewTag(Tag tag) {
        return tagRepository.save(tag);
    }

    public ResponseEntity<?> getTagById(long id) {
        Optional<Tag> optionalTag = tagRepository.findById(id);
        if (optionalTag.isPresent()) {
            return ResponseEntity.ok(optionalTag.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

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