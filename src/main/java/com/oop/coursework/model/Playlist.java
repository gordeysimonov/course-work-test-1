package com.oop.coursework.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Objects;
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
            inverseJoinColumns = { @JoinColumn(name="musicFileId", referencedColumnName = "id") },
            joinColumns = { @JoinColumn(name="playlistId", referencedColumnName = "id") }
    )
    private Set<MusicFile> musicFiles;

    @ManyToOne
    @JoinColumn(nullable = false)
    private AppUser userId;

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Playlist{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", userId=" + userId.getUsername() +
                '}';
    }

}
