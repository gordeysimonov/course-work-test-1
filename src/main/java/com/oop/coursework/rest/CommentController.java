package com.oop.coursework.rest;

import com.oop.coursework.annotation.LogController;
import com.oop.coursework.model.Comment;
import com.oop.coursework.services.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class CommentController {

    private final CommentService commentService;

    @Autowired
    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @LogController
    @PostMapping("comment")
    public ResponseEntity<?> createNewComment(@RequestBody Comment comment){
        commentService.createNewComment(comment);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @LogController
    @GetMapping("comment")
    public ResponseEntity<?> getCommentById(@RequestParam(value = "id") long id) {
        return commentService.getCommentById(id);
    }

    @LogController
    @PutMapping("comment")
    public ResponseEntity<?> updateComment(@RequestBody Comment newCommentData) {
        return commentService.updateComment(newCommentData.getId(), newCommentData);
    }

    @LogController
    @DeleteMapping("comment")
    public ResponseEntity<?> deleteComment(@RequestParam(value = "id") long id) {
        return commentService.deleteComment(id);
    }

    @LogController
    @GetMapping("comment/get-comment-branch")
    public ResponseEntity<?> getCommentBranchById(@RequestParam(value = "id") long id) {
        return commentService.getCommentBranchById(id);
    }

    @LogController
    @GetMapping("comment/get-file-comments")
    public ResponseEntity<?> getCommentsByFileId(@RequestParam(value = "id") long id) {
        return commentService.getCommentsByFileId(id);
    }

}
