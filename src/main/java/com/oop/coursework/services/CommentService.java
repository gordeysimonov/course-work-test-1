package com.oop.coursework.services;

import com.oop.coursework.model.Comment;
import com.oop.coursework.repo.CommentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class CommentService {

    private CommentRepo commentRepository;

    @Autowired
    public CommentService(CommentRepo commentRepository) {
        this.commentRepository = commentRepository;
    }

    public Comment createNewComment(Comment comment) {
        comment.setPostDate(LocalDateTime.now());
        return commentRepository.save(comment);
    }

    public ResponseEntity<?> getCommentById(long id) {
        Optional<Comment> optionalComment = commentRepository.findById(id);
        if (optionalComment.isPresent()) {
            return ResponseEntity.ok(optionalComment.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    public ResponseEntity<?> updateComment(long id, Comment newCommentData) {
        Optional<Comment> optionalComment = commentRepository.findById(id);
        if (optionalComment.isPresent()) {
            Comment existingComment = optionalComment.get();
            existingComment.setCommentText(newCommentData.getCommentText());
            if(newCommentData.getUserId() != null) {
                existingComment.setUserId(newCommentData.getUserId());
            }
            if(newCommentData.getMusicFileId() != null) {
                existingComment.setMusicFileId(newCommentData.getMusicFileId());
            }
            commentRepository.save(existingComment);
            return ResponseEntity.ok(existingComment);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    public ResponseEntity<?> deleteComment(long id) {
        Optional<Comment> optionalComment = commentRepository.findById(id);
        if (optionalComment.isPresent()) {
            commentRepository.deleteById(id);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
