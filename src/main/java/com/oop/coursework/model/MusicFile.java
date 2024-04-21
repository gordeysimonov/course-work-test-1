package com.oop.coursework.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Data
public class MusicFile {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String author;
    private int year;
    private String description;
    private String lyrics;
    private String filePath;
    private FileType fileType;
    private LocalDateTime downloadDate;
    private int downloadsNumber;
    @ManyToOne
    @JoinColumn(nullable = false)
    private AppUser userId;
    @OneToMany(mappedBy = "musicFileId", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Rate> musicFileRatingList;
    @OneToMany(mappedBy = "musicFileId", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Comment> musicFileCommentList;

    @ManyToMany
    @JoinTable(
            name = "musicFileGenres",
            joinColumns = @JoinColumn(name="musicFileId", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name="genreId", referencedColumnName = "id")
    )
    private Set<Genre> genres;

    @ManyToMany
    @JoinTable(
            name = "musicFileTags",
            joinColumns = @JoinColumn(name="musicFileId", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name="tagId", referencedColumnName = "id")
    )
    private Set<Tag> tags;

    @JsonIgnore
    @ManyToMany(mappedBy = "musicFiles")
    private Set<Category> categories;

    @JsonIgnore
    @ManyToMany(mappedBy = "musicFiles")
    private Set<Playlist> playlists;

    public enum FileType {
        MP3,
        WAV,
        OTHER
    }

}
