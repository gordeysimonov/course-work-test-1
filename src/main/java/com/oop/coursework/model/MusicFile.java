package com.oop.coursework.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
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
    @Enumerated(EnumType.STRING)
    @Column(name = "fileType", length = 5)
    private FileType fileType;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime downloadDate;
    private int downloadsNumber;
    @ManyToOne
    @JoinColumn(nullable = false)
    private AppUser userId;
    @OneToMany(mappedBy = "musicFileId", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonIgnore
    private List<Rate> musicFileRatingList;
    private double averageRate;
    @OneToMany(mappedBy = "musicFileId", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonIgnore
    private List<Comment> musicFileCommentList;
    private int commentsNumber;

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
    @ManyToMany(mappedBy = "musicFiles", cascade = CascadeType.ALL)
    private Set<Category> categories;

    @JsonIgnore
    @ManyToMany(mappedBy = "musicFiles", cascade = CascadeType.ALL)
    private Set<Playlist> playlists;

    public enum FileType {
        MP3,
        WAV,
        OTHER
    }

    public void updateAverageRate() {
        if (musicFileRatingList != null && !musicFileRatingList.isEmpty()) {
            double sum = 0;
            for (Rate rate : musicFileRatingList) {
                sum += rate.getRate().getValue();
            }
            averageRate = sum / musicFileRatingList.size();
            System.out.println("Average rate updated: " + averageRate); // Додайте логування
        } else {
            averageRate = 0;
        }
    }

    @PostLoad
    public void postLoad() {
        updateAverageRate();
    }

    @PrePersist
    @PreUpdate
    public void prePersistOrUpdate() {
        updateAverageRate();
    }

}
