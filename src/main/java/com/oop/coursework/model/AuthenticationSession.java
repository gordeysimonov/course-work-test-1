package com.oop.coursework.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
public class AuthenticationSession {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private LocalDateTime sessionStart;
    private LocalDateTime sessionEnd;

    @ManyToOne
    @JoinColumn(nullable = false)
    private AppUser userId;

}
