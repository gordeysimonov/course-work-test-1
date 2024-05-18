package com.oop.coursework.repo;

import com.oop.coursework.annotation.LogRepository;
import com.oop.coursework.model.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface NotificationRepo extends JpaRepository<Notification, Long> {

    @LogRepository
    @Query("SELECT json_build_object(" +
            "'id', n.id, " +
            "'notificationType', n.notificationType, " +
            "'notificationText', n.notificationText, " +
            "'status', n.status, " +
            "'dateReceiving', n.dateReceiving, " +
            "'user', (SELECT json_agg(json_build_object('id', u.id, 'username', u.username)) FROM n.userId u) " +
            ") " +
            "FROM Notification n WHERE n.userId.id = :userId")
    List<Object[]> findByUserId(@Param("userId") Long userId);

    @LogRepository
    @Query("SELECT json_build_object(" +
            "'id', n.id, " +
            "'notificationType', n.notificationType, " +
            "'notificationText', n.notificationText, " +
            "'status', n.status, " +
            "'dateReceiving', n.dateReceiving, " +
            "'user', (SELECT json_agg(json_build_object('id', u.id, 'username', u.username)) FROM n.userId u) " +
            ") " +
            "FROM Notification n WHERE n.userId.id = :userId AND n.status = 'unread'")
    List<Object[]> findByUserIdAndStatus(@Param("userId") Long userId);

    @LogRepository
    @Query("SELECT json_build_object(" +
            "'id', n.id, " +
            "'notificationType', n.notificationType, " +
            "'notificationText', n.notificationText, " +
            "'status', n.status, " +
            "'dateReceiving', n.dateReceiving, " +
            "'user', (SELECT json_agg(json_build_object('id', u.id, 'username', u.username)) FROM n.userId u) " +
            ") " +
            "FROM Notification n WHERE n.id = :id")
    List<Object[]> findNotificationById(@Param("id") Long id);

}
