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
    @ManyToMany(mappedBy = "genres", cascade = CascadeType.ALL)
    private Set<MusicFile> musicFiles;

}
