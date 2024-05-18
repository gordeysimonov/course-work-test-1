package com.oop.coursework.rest;

import com.oop.coursework.annotation.LogController;
import com.oop.coursework.model.AuthenticationSession;
import com.oop.coursework.services.AuthenticationSessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class AuthenticationSessionController {

    private final AuthenticationSessionService authenticationSessionService;

    @Autowired
    public AuthenticationSessionController(AuthenticationSessionService authenticationSessionService) {
        this.authenticationSessionService = authenticationSessionService;
    }

    @LogController
    @PostMapping("authentication-session")
    public ResponseEntity<?> createNewAuthenticationSession(@RequestBody AuthenticationSession authenticationSession){
        authenticationSessionService.createNewAuthenticationSession(authenticationSession);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @LogController
    @GetMapping("authentication-session")
    public ResponseEntity<?> getAuthenticationSessionById(@RequestParam(value = "id") long id) {
        return authenticationSessionService.getAuthenticationSessionById(id);
    }

    @LogController
    @GetMapping("authentication-session/get-sessions")
    public ResponseEntity<?> getAuthenticationSessions() {
        return authenticationSessionService.getAuthenticationSessions();
    }

    @LogController
    @PutMapping("authentication-session")
    public ResponseEntity<?> updateAuthenticationSession(@RequestBody AuthenticationSession newAuthenticationSessionData) {
        return authenticationSessionService.updateAuthenticationSession(newAuthenticationSessionData.getId(), newAuthenticationSessionData);
    }

    @LogController
    @DeleteMapping("authentication-session")
    public ResponseEntity<?> deleteAuthenticationSession(@RequestParam(value = "id") long id) {
        return authenticationSessionService.deleteAuthenticationSession(id);
    }

}
