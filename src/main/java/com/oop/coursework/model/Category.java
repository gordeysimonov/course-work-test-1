package com.oop.coursework.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.*;

@Entity
@Data
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String description;
    private String imageUrl;

    @ManyToMany
    @JoinTable(
            name = "musicFileCategories",
            inverseJoinColumns = { @JoinColumn(name="musicFileId", referencedColumnName = "id") },
            joinColumns = { @JoinColumn(name="categoryId", referencedColumnName = "id") }
    )
    private List<MusicFile> musicFiles;

}
