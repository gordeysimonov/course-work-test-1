package com.oop.coursework.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String commentText;
    private LocalDateTime postDate;

    @ManyToOne
    @JoinColumn(nullable = false)
    private AppUser userId;

    @ManyToOne
    @JoinColumn(nullable = false)
    private MusicFile musicFileId;

}
