package com.oop.coursework.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

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

/*
    @PreRemove
    private void removeGenresFromFiles() {
        for (MusicFile musicFile : musicFiles) {
            musicFile.getGenres().remove(this);
        }
    }
*/

}
