package com.oop.coursework.repo;

import com.oop.coursework.model.AuthenticationSession;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthenticationSessionRepo extends JpaRepository<AuthenticationSession, Long> {
}

