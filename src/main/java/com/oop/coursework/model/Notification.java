package com.oop.coursework.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
public class Notification {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String notificationType;
    private String notificationText;
    private String status;
    private LocalDateTime dateReceiving;

    @ManyToOne
    @JoinColumn(nullable = false)
    private AppUser userId;

}
