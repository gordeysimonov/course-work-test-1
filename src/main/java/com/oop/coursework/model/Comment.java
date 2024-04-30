package com.oop.coursework.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Data
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String commentText;

    @OneToMany(mappedBy = "reply", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonIgnore
    private Set<Comment> replies = new HashSet<>();

    @ManyToOne
    @JoinColumn
    @JsonIgnore
    private Comment reply;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime postDate;

    @ManyToOne
    @JoinColumn(nullable = false)
    private AppUser userId;

    @ManyToOne
    @JoinColumn(nullable = false)
    private MusicFile musicFileId;

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}
