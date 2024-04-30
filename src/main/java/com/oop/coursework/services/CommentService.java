package com.oop.coursework.services;

import com.oop.coursework.dto.CommentDTO;
import com.oop.coursework.model.AuthenticationSession;
import com.oop.coursework.model.Comment;
import com.oop.coursework.model.MusicFile;
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

    public void createNewComment(Comment comment) {
        comment.setPostDate(LocalDateTime.now());
        Comment savedComment = commentRepository.save(comment);
        savedComment.getReply().getReplies().add(savedComment);
        if (savedComment.getReply() != null) {
            savedComment.getReply().getReplies().add(savedComment);
            commentRepository.save(savedComment.getReply());
        }
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
            if(newCommentData.getCommentText() != null) {
                existingComment.setCommentText(newCommentData.getCommentText());
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

    private void deleteCommentBranch(Comment comment) {
        Set<Comment> repliesCopy = new HashSet<>(comment.getReplies());
        for (Comment reply : repliesCopy) {
            deleteCommentBranch(reply);
            comment.getReplies().remove(reply);
            commentRepository.deleteCommentById(reply.getId());
        }
    }

    public ResponseEntity<?> getCommentBranchById(long id) {
        List<CommentDTO> commentBranchDTO = new ArrayList<>();
        getCommentBranch(id, commentBranchDTO);
        if (!commentBranchDTO.isEmpty()) {
            return ResponseEntity.ok(commentBranchDTO);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

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

    public ResponseEntity<?> getCommentsByFileId(long id) {
        Optional<MusicFile> optionalFile = musicFileRepository.findById(id);
        if (optionalFile.isPresent()) {
            MusicFile musicFile = optionalFile.get();
            List<CommentDTO> commentDTOs = new ArrayList<>();
            for (Comment comment : musicFile.getMusicFileCommentList()) {
                CommentDTO commentDTO = new CommentDTO();
                commentDTO.setId(comment.getId());
                if (comment.getReply() != null) {
                    commentDTO.setReplyId(comment.getReply().getId());
                }
                if (comment.getCommentText() != null) {
                    commentDTO.setCommentText(comment.getCommentText());
                }
                if (comment.getPostDate() != null) {
                    commentDTO.setPostDate(comment.getPostDate());
                }
                if (comment.getUserId() != null) {
                    commentDTO.setUserId(comment.getUserId().getId());
                }
                if (comment.getMusicFileId() != null) {
                    commentDTO.setMusicFileId(comment.getMusicFileId().getId());
                }
                commentDTOs.add(commentDTO);
            }
            return ResponseEntity.ok(commentDTOs);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
