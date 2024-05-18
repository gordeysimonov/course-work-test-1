package com.oop.coursework.services;

import com.oop.coursework.annotation.LogService;
import com.oop.coursework.model.AuthenticationSession;
import com.oop.coursework.repo.AuthenticationSessionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AuthenticationSessionService {

    private final AuthenticationSessionRepo authenticationSessionRepository;

    @Autowired
    public AuthenticationSessionService(AuthenticationSessionRepo authenticationSessionRepository) {
        this.authenticationSessionRepository = authenticationSessionRepository;
    }

    @LogService
    public void createNewAuthenticationSession(AuthenticationSession authenticationSession) {
        authenticationSessionRepository.save(authenticationSession);
    }

    @LogService
    public ResponseEntity<?> getAuthenticationSessionById(long id) {
        List<Object[]> sessions = authenticationSessionRepository.findAuthenticationSessionById(id);
        return ResponseEntity.ok(sessions);
    }

    @LogService
    public ResponseEntity<?> getAuthenticationSessions() {
        List<Object[]> sessions = authenticationSessionRepository.findAllAuthenticationSessions();
        return ResponseEntity.ok(sessions);
    }

    @LogService
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

    @LogService
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
