package com.oop.coursework.services;

import com.oop.coursework.model.AuthenticationSession;
import com.oop.coursework.repo.AuthenticationSessionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthenticationSessionService {

    private AuthenticationSessionRepo authenticationSessionRepository;

    @Autowired
    public AuthenticationSessionService(AuthenticationSessionRepo authenticationSessionRepository) {
        this.authenticationSessionRepository = authenticationSessionRepository;
    }

    public AuthenticationSession createNewAuthenticationSession(AuthenticationSession authenticationSession) {
        return authenticationSessionRepository.save(authenticationSession);
    }

    public ResponseEntity<?> getAuthenticationSessionById(long id) {
        Optional<AuthenticationSession> optionalAuthenticationSession = authenticationSessionRepository.findById(id);
        if (optionalAuthenticationSession.isPresent()) {
            return ResponseEntity.ok(optionalAuthenticationSession.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    public ResponseEntity<?> updateAuthenticationSession(long id, AuthenticationSession newAuthenticationSessionData) {
        Optional<AuthenticationSession> optionalAuthenticationSession = authenticationSessionRepository.findById(id);
        if (optionalAuthenticationSession.isPresent()) {
            AuthenticationSession existingAuthenticationSession = optionalAuthenticationSession.get();
            existingAuthenticationSession.setSessionStart(newAuthenticationSessionData.getSessionStart());
            existingAuthenticationSession.setSessionEnd(newAuthenticationSessionData.getSessionEnd());
            if(newAuthenticationSessionData.getUserId() != null) {
                existingAuthenticationSession.setUserId(newAuthenticationSessionData.getUserId());
            }
            authenticationSessionRepository.save(existingAuthenticationSession);
            return ResponseEntity.ok(existingAuthenticationSession);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    public ResponseEntity<?> deleteAuthenticationSession(long id) {
        Optional<AuthenticationSession> optionalAuthenticationSession = authenticationSessionRepository.findById(id);
        if (optionalAuthenticationSession.isPresent()) {
            authenticationSessionRepository.deleteById(id);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
