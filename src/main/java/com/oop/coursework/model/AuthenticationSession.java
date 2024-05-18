package com.oop.coursework.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Data
public class AuthenticationSession {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime sessionStart;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime sessionEnd;

    @ManyToOne
    @JoinColumn(nullable = false)
    private AppUser userId;

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "AuthenticationSession{" +
                "id=" + id +
                ", sessionStart=" + sessionStart +
                ", sessionEnd=" + sessionEnd +
                ", userId=" + userId.getUsername() +
                '}';
    }

}
