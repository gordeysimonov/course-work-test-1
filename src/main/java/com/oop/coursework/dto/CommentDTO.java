package com.oop.coursework.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CommentDTO {
    private Long id;
    private Long replyId;
    private String commentText;
    private LocalDateTime postDate;
    private Long userId;
    private Long musicFileId;
}

