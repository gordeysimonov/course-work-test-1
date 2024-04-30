package com.oop.coursework.rest;

import com.oop.coursework.model.AppUser;
import com.oop.coursework.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/user")
    public ResponseEntity<?> createNewUser(@RequestBody AppUser user){
        userService.createNewUser(user);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/user")
    public ResponseEntity<?> getUserById(@RequestParam(value = "id") Long id) {
        return userService.getUserById(id);
    }

    @GetMapping("/user/get-users")
    public ResponseEntity<?> getUsers() {
        return userService.getAllUsers();
    }

    @PutMapping("/user")
    public ResponseEntity<?> updateUser(@RequestBody AppUser newUserData) {
        return userService.updateUser(newUserData.getId(), newUserData);
    }

    @DeleteMapping("/user")
    public ResponseEntity<?> deleteUser(@RequestParam(value = "id") long id) {
        return userService.deleteUser(id);
    }

}
