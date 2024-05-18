package com.oop.coursework.services;


import com.oop.coursework.annotation.LogService;
import com.oop.coursework.model.AppUser;
import com.oop.coursework.model.Comment;
import com.oop.coursework.repo.AppUserRepo;
import com.oop.coursework.repo.CommentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class UserService {

    private final AppUserRepo userRepository;
    private final CommentRepo commentRepository;

    @Autowired
    public UserService(AppUserRepo userRepository, CommentRepo commentRepository) {
        this.userRepository = userRepository;
        this.commentRepository = commentRepository;
    }

    @LogService
    public void createNewUser(AppUser user) {
        user.setRegistrationDate(LocalDateTime.now());
        user.setStatus("online");
        userRepository.save(user);
    }

    @LogService
    public ResponseEntity<?> getUserById(long id) {
        List<Object[]> users = userRepository.findUserById(id);
        return ResponseEntity.ok(users);
    }

    @LogService
    public ResponseEntity<?> getAllUsers() {
        List<Object[]> users = userRepository.findAllUsers();
        return ResponseEntity.ok(users);
    }

    @LogService
    public ResponseEntity<?> updateUser(long id, AppUser newUserData) {
        Optional<AppUser> optionalUser = userRepository.findById(id);
        if (optionalUser.isPresent()) {
            AppUser existingUser = optionalUser.get();
            if(newUserData.getUsername() != null) {
                existingUser.setUsername(newUserData.getUsername());
            }
            if(newUserData.getEmail() != null) {
                existingUser.setEmail(newUserData.getEmail());
            }
            existingUser.setPassword(newUserData.getPassword());
            if(newUserData.getRole() != null) {
                existingUser.setRole(newUserData.getRole());
            }
            if(newUserData.getPhotoUrl() != null) {
                existingUser.setPhotoUrl(newUserData.getPhotoUrl());
            }
            if(newUserData.getSubscribers() != null) {
                existingUser.setSubscribers(newUserData.getSubscribers());
            }
            if(newUserData.getSubscriptions() != null) {
                existingUser.setSubscriptions(newUserData.getSubscriptions());
            }
            if(newUserData.getUserMusicFilesList() != null) {
                existingUser.setUserMusicFilesList(newUserData.getUserMusicFilesList());
            }
            if(newUserData.getUserRatesList() != null) {
                existingUser.setUserRatesList(newUserData.getUserRatesList());
            }
            if(newUserData.getUserCommentsList() != null) {
                existingUser.setUserCommentsList(newUserData.getUserCommentsList());
            }
            if(newUserData.getUserPlaylistsList() != null) {
                existingUser.setUserPlaylistsList(newUserData.getUserPlaylistsList());
            }
            if(newUserData.getUserNotificationsList() != null) {
                existingUser.setUserNotificationsList(newUserData.getUserNotificationsList());
            }
            if(newUserData.getUserAuthenticationSessionsList() != null) {
                existingUser.setUserAuthenticationSessionsList(newUserData.getUserAuthenticationSessionsList());
            }
            userRepository.save(existingUser);
            return ResponseEntity.ok(existingUser);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @LogService
    public ResponseEntity<?> deleteUser(long id) {
        Optional<AppUser> optionalUser = userRepository.findById(id);
        if (optionalUser.isPresent()) {
            AppUser user = optionalUser.get();

            Set<Comment> userComments = user.getUserCommentsList();

            for (Comment comment : userComments) {
                deleteCommentBranchByUser(comment);
            }

            userRepository.deleteById(id);

            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @LogService
    private void deleteCommentBranchByUser(Comment comment) {
        Set<Comment> repliesCopy = new HashSet<>(comment.getReplies());
        for (Comment reply : repliesCopy) {
            deleteCommentBranchByUser(reply);
            comment.getReplies().remove(reply);
            commentRepository.deleteCommentById(reply.getId());
        }
        commentRepository.deleteCommentById(comment.getId());
    }

}
