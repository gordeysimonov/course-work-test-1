package com.oop.coursework.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.oop.coursework.annotation.LogModel;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Objects;
import java.util.Set;

@Entity
@Data
public class Genre {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String genre;
    private String description;

    @JsonIgnore
    @ManyToMany(mappedBy = "genres")
    private Set<MusicFile> musicFiles;

    @LogModel
    @PreRemove
    private void removeGenresFromFiles() {
        for (MusicFile musicFile : musicFiles) {
            musicFile.getGenres().remove(this);
            this.musicFiles.remove(musicFile);
        }
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Genre{" +
                "id=" + id +
                ", genre='" + genre + '\'' +
                ", description='" + description + '\'' +
                '}';
    }

}
