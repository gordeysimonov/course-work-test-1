package com.oop.coursework.rest;

import com.oop.coursework.model.Comment;
import com.oop.coursework.services.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class CommentController {

    private final CommentService commentService;

    @Autowired
    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping("comment")
    public ResponseEntity<?> createNewComment(@RequestBody Comment comment){
        return ResponseEntity.ok(commentService.createNewComment(comment));
    }

    @GetMapping("comment")
    public ResponseEntity<?> getCommentById(@RequestParam(value = "id") long id) {
        return commentService.getCommentById(id);
    }

    @PutMapping("comment")
    public ResponseEntity<?> updateComment(@RequestBody Comment newCommentData) {
        return commentService.updateComment(newCommentData.getId(), newCommentData);
    }

    @DeleteMapping("comment")
    public ResponseEntity<?> deleteComment(@RequestParam(value = "id") long id) {
        return commentService.deleteComment(id);
    }

}
