package com.oop.coursework.repo;


import com.oop.coursework.annotation.LogRepository;
import com.oop.coursework.model.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AppUserRepo extends JpaRepository<AppUser, Long> {

    @LogRepository
    @Query("SELECT json_build_object(" +
            "'id', u.id, " +
            "'username', u.username, " +
            "'email', u.email, " +
            "'role', u.role, " +
            "'photoUrl', u.photoUrl, " +
            "'registrationDate', u.registrationDate, " +
            "'status', u.status, " +
            "'subscriptionsCount', (SELECT COUNT(sub.id) FROM Subscription sub WHERE sub.subscriber = u), " +
            "'subscribersCount', (SELECT COUNT(sub.id) FROM Subscription sub WHERE sub.subscribedTo = u) " +
            ") " +
            "FROM AppUser u WHERE u.id = :id")
    List<Object[]> findUserById(@Param("id") Long id);

    @LogRepository
    @Query("SELECT json_build_object(" +
            "'id', u.id, " +
            "'username', u.username, " +
            "'email', u.email, " +
            "'role', u.role, " +
            "'photoUrl', u.photoUrl, " +
            "'registrationDate', u.registrationDate, " +
            "'status', u.status, " +
            "'subscriptionsCount', (SELECT COUNT(sub.id) FROM Subscription sub WHERE sub.subscriber = u), " +
            "'subscribersCount', (SELECT COUNT(sub.id) FROM Subscription sub WHERE sub.subscribedTo = u) " +
            ") " +
            "FROM AppUser u")
    List<Object[]> findAllUsers();

}
