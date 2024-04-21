package com.oop.coursework.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Set;

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
            inverseJoinColumns = { @JoinColumn(name="musicFileId", referencedColumnName = "id", updatable = false, insertable = false) },
            joinColumns = { @JoinColumn(name="categoryId", referencedColumnName = "id", updatable = false, insertable = false) }
    )
    private Set<MusicFile> musicFiles;

}
