package com.oop.coursework.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.oop.coursework.annotation.LogModel;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
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

    @LogModel
    public void updateAverageRate() {
        if (musicFileRatingList != null && !musicFileRatingList.isEmpty()) {
            double sum = 0;
            for (Rate rate : musicFileRatingList) {
                sum += rate.getRate().getValue();
            }
            averageRate = sum / musicFileRatingList.size();
            System.out.println("Average rate updated: " + averageRate);
        } else {
            averageRate = 0;
        }
    }

    @LogModel
    @PrePersist
    @PreUpdate
    @PostLoad
    public void prePersistOrUpdate() {
        updateAverageRate();
    }

    @LogModel
    public void removeRate(Rate rate) {
        musicFileRatingList.remove(rate);
        updateAverageRate();
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "MusicFile{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", author='" + author + '\'' +
                ", year=" + year +
                ", description='" + description + '\'' +
                ", lyrics='" + lyrics + '\'' +
                ", filePath='" + filePath + '\'' +
                ", fileType=" + fileType +
                ", downloadDate=" + downloadDate +
                ", downloadsNumber=" + downloadsNumber +
                ", userId=" + userId.getUsername() +
                ", averageRate=" + averageRate +
                '}';
    }

}
