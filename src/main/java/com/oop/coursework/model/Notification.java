package com.oop.coursework.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Data
public class Notification {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String notificationType;
    private String notificationText;
    private String status;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime dateReceiving;

    @ManyToOne
    @JoinColumn(nullable = false)
    private AppUser userId;

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Notification{" +
                "id=" + id +
                ", notificationType='" + notificationType + '\'' +
                ", notificationText='" + notificationText + '\'' +
                ", status='" + status + '\'' +
                ", dateReceiving=" + dateReceiving +
                ", userId=" + userId.getUsername() +
                '}';
    }

}
