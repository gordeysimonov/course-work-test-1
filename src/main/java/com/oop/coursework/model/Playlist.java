package com.oop.coursework.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Set;

@Entity
@Data
public class Playlist {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String description;

    @ManyToMany
    @JoinTable(
            name = "musicFilePlaylists",
            inverseJoinColumns = { @JoinColumn(name="musicFileId", referencedColumnName = "id", updatable = false, insertable = false) },
            joinColumns = { @JoinColumn(name="playlistId", referencedColumnName = "id", updatable = false, insertable = false) }
    )
    private Set<MusicFile> musicFiles;
    @ManyToOne
    @JoinColumn(nullable = false)
    private AppUser userId;

}
