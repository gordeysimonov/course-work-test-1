package com.oop.coursework.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
public class AppUser {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String username;
    private String email;
    private String password;
    private String role;
    private String photoUrl;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime registrationDate;
    private String status;

    @OneToMany(mappedBy = "subscriber", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Subscription> subscriptions;
    @OneToMany(mappedBy = "subscribedTo", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Subscription> subscribers;
    @OneToMany(mappedBy = "userId", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Rate> userRatesList;
    @OneToMany(mappedBy = "userId", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Comment> userCommentsList;
    @OneToMany(mappedBy = "userId", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<MusicFile> userMusicFilesList;
    @OneToMany(mappedBy = "userId", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Playlist> userPlaylistsList;
    @OneToMany(mappedBy = "userId", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Notification> userNotificationsList;
    @OneToMany(mappedBy = "userId", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<AuthenticationSession> userAuthenticationSessionsList;

}
