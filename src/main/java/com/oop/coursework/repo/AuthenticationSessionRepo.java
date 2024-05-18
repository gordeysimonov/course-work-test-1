package com.oop.coursework.repo;

import com.oop.coursework.annotation.LogRepository;
import com.oop.coursework.model.AuthenticationSession;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AuthenticationSessionRepo extends JpaRepository<AuthenticationSession, Long> {

    @LogRepository
    @Query("SELECT json_build_object(" +
            "'id', aus.id, " +
            "'sessionStart', aus.sessionStart, " +
            "'sessionEnd', aus.sessionEnd, " +
            "'userId', (SELECT json_agg(json_build_object('id', u.id, 'username', u.username)) FROM aus.userId u) " +
            ") " +
            "FROM AuthenticationSession aus WHERE aus.id = :id")
    List<Object[]> findAuthenticationSessionById(@Param("id") Long id);

    @LogRepository
    @Query("SELECT json_build_object(" +
            "'id', aus.id, " +
            "'sessionStart', aus.sessionStart, " +
            "'sessionEnd', aus.sessionEnd, " +
            "'userId', (SELECT json_agg(json_build_object('id', u.id, 'username', u.username)) FROM aus.userId u) " +
            ") " +
            "FROM AuthenticationSession aus")
    List<Object[]> findAllAuthenticationSessions();

}

