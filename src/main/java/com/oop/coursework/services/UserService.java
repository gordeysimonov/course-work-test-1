package com.oop.coursework.services;


import com.oop.coursework.model.AppUser;
import com.oop.coursework.repo.AppUserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class UserService {

    private AppUserRepo userRepository;

    @Autowired
    public UserService(AppUserRepo userRepository) {
        this.userRepository = userRepository;
    }

    public AppUser createNewUser(AppUser user) {
        user.setRegistrationDate(LocalDateTime.now());
        user.setStatus("online");
        return userRepository.save(user);
    }

    public ResponseEntity<?> getUserById(long id) {
        Optional<AppUser> optionalUser = userRepository.findById(id);
        if (optionalUser.isPresent()) {
            return ResponseEntity.ok(optionalUser.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    public ResponseEntity<?> updateUser(long id, AppUser newUserData) {
        Optional<AppUser> optionalUser = userRepository.findById(id);
        if (optionalUser.isPresent()) {
            AppUser existingUser = optionalUser.get();
            existingUser.setUsername(newUserData.getUsername());
            existingUser.setEmail(newUserData.getEmail());
            existingUser.setPassword(newUserData.getPassword());
            existingUser.setRole(newUserData.getRole());
            existingUser.setPhotoUrl(newUserData.getPhotoUrl());
            if(newUserData.getUserSubscriptionsList() != null) {
                existingUser.setUserSubscriptionsList(newUserData.getUserSubscriptionsList());
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

    public ResponseEntity<?> deleteUser(long id) {
        Optional<AppUser> optionalUser = userRepository.findById(id);
        if (optionalUser.isPresent()) {
            userRepository.deleteById(id);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
