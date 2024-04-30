package com.oop.coursework.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Set;

@Entity
@Data
public class Tag {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String tagName;

    @JsonIgnore
    @ManyToMany(mappedBy = "tags", cascade = CascadeType.ALL)
    private Set<MusicFile> musicFiles;

}
