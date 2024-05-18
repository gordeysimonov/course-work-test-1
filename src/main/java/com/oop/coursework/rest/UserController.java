package com.oop.coursework.rest;

import com.oop.coursework.annotation.LogController;
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

    @LogController
    @PostMapping("/user")
    public ResponseEntity<?> createNewUser(@RequestBody AppUser user){
        userService.createNewUser(user);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @LogController
    @GetMapping("/user")
    public ResponseEntity<?> getUserById(@RequestParam(value = "id") Long id) {
        return userService.getUserById(id);
    }

    @LogController
    @GetMapping("/user/get-users")
    public ResponseEntity<?> getUsers() {
        return userService.getAllUsers();
    }

    @LogController
    @PutMapping("/user")
    public ResponseEntity<?> updateUser(@RequestBody AppUser newUserData) {
        return userService.updateUser(newUserData.getId(), newUserData);
    }

    @LogController
    @DeleteMapping("/user")
    public ResponseEntity<?> deleteUser(@RequestParam(value = "id") long id) {
        return userService.deleteUser(id);
    }

}
