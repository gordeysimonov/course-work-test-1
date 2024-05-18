package com.oop.coursework.services;

import com.oop.coursework.annotation.LogService;
import com.oop.coursework.dto.CommentDTO;
import com.oop.coursework.model.Comment;
import com.oop.coursework.repo.CommentRepo;
import com.oop.coursework.repo.MusicFileRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;

@Service
public class CommentService {

    private final CommentRepo commentRepository;
    private final MusicFileRepo musicFileRepository;

    @Autowired
    public CommentService(CommentRepo commentRepository, MusicFileRepo musicFileRepository) {
        this.commentRepository = commentRepository;
        this.musicFileRepository = musicFileRepository;
    }

    @LogService
    public void createNewComment(Comment comment) {
        comment.setPostDate(LocalDateTime.now());
        Comment savedComment = commentRepository.save(comment);
        if (savedComment.getReply() != null) {
            savedComment.getReply().getReplies().add(savedComment);
            commentRepository.save(savedComment.getReply());
        }
    }

    @LogService
    public ResponseEntity<?> getCommentById(long id) {
        List<Object[]> comments = commentRepository.findCommentById(id);
        return ResponseEntity.ok(comments);
    }

    @LogService
    public ResponseEntity<?> updateComment(long id, Comment newCommentData) {
        Optional<Comment> optionalComment = commentRepository.findById(id);
        if (optionalComment.isPresent()) {
            Comment existingComment = optionalComment.get();
            if(newCommentData.getCommentText() != null) {
                existingComment.setCommentText(newCommentData.getCommentText());
            }
            if(newCommentData.getReply() != null) {
                existingComment.setReply(newCommentData.getReply());
            }
            if(newCommentData.getPostDate() != null) {
                existingComment.setPostDate(newCommentData.getPostDate());
            }
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

    @LogService
    public ResponseEntity<?> deleteComment(long id) {
        Optional<Comment> optionalComment = commentRepository.findById(id);
        if (optionalComment.isPresent()) {
            Comment comment = optionalComment.get();
            deleteCommentBranch(comment);
            commentRepository.deleteCommentById(comment.getId());
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @LogService
    private void deleteCommentBranch(Comment comment) {
        Set<Comment> repliesCopy = new HashSet<>(comment.getReplies());
        for (Comment reply : repliesCopy) {
            deleteCommentBranch(reply);
            comment.getReplies().remove(reply);
            commentRepository.deleteCommentById(reply.getId());
        }
    }

    @LogService
    public ResponseEntity<?> getCommentBranchById(long id) {
        List<CommentDTO> commentBranchDTO = new ArrayList<>();
        getCommentBranch(id, commentBranchDTO);
        if (!commentBranchDTO.isEmpty()) {
            return ResponseEntity.ok(commentBranchDTO);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @LogService
    private void getCommentBranch(long id, List<CommentDTO> commentBranchDTO) {
        Optional<Comment> optionalComment = commentRepository.findById(id);
        if (optionalComment.isPresent()) {
            Comment comment = optionalComment.get();
            CommentDTO commentDTO = new CommentDTO();
            commentDTO.setId(comment.getId());
            if (comment.getReply() != null) {
                commentDTO.setReplyId(comment.getReply().getId());
            }
            if (comment.getCommentText() != null) {
                commentDTO.setCommentText(comment.getCommentText());
            }
            if (comment.getCommentText() != null) {
                commentDTO.setPostDate(comment.getPostDate());
            }
            if (comment.getUserId().getId() != null) {
                commentDTO.setUserId(comment.getUserId().getId());
            }
            if (comment.getMusicFileId().getId() != null) {
                commentDTO.setMusicFileId(comment.getMusicFileId().getId());
            }
            commentBranchDTO.add(commentDTO);
            for (Comment reply : comment.getReplies()) {
                getCommentBranch(reply.getId(), commentBranchDTO);
            }
        }
    }

    @LogService
    public ResponseEntity<?> getCommentsByFileId(Long id) {
        List<Object[]> comments = commentRepository.findCommentsByFileId(id);
        return ResponseEntity.ok(comments);
    }

}
