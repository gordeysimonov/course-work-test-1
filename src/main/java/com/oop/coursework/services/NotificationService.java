package com.oop.coursework.services;

import com.oop.coursework.annotation.LogService;
import com.oop.coursework.model.Notification;
import com.oop.coursework.repo.NotificationRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class NotificationService {

    private final NotificationRepo notificationRepository;

    @Autowired
    public NotificationService(NotificationRepo notificationRepository) {
        this.notificationRepository = notificationRepository;
    }

    @LogService
    public void createNewNotification(Notification notification) {
        notification.setDateReceiving(LocalDateTime.now());
        notificationRepository.save(notification);
    }

    @LogService
    public ResponseEntity<?> getNotificationById(long id) {
        List<Object[]> notifications = notificationRepository.findNotificationById(id);
        return ResponseEntity.ok(notifications);
    }

    @LogService
    public ResponseEntity<?> updateNotification(long id, Notification newNotificationData) {
        Optional<Notification> optionalNotification = notificationRepository.findById(id);
        if (optionalNotification.isPresent()) {
            Notification existingNotification = optionalNotification.get();
            if(newNotificationData.getNotificationType() != null) {
                existingNotification.setNotificationType(newNotificationData.getNotificationType());
            }
            if(newNotificationData.getNotificationText() != null) {
                existingNotification.setNotificationText(newNotificationData.getNotificationText());
            }
            if(newNotificationData.getStatus() != null) {
                existingNotification.setStatus(newNotificationData.getStatus());
            }
            if(newNotificationData.getDateReceiving() != null) {
                existingNotification.setDateReceiving(newNotificationData.getDateReceiving());
            }
            if(newNotificationData.getUserId() != null) {
                existingNotification.setUserId(newNotificationData.getUserId());
            }
            notificationRepository.save(existingNotification);
            return ResponseEntity.ok(existingNotification);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @LogService
    public ResponseEntity<?> deleteNotification(long id) {
        Optional<Notification> optionalNotification = notificationRepository.findById(id);
        if (optionalNotification.isPresent()) {
            notificationRepository.deleteById(id);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @LogService
    public ResponseEntity<?> getUserNotifications(Long id) {
        List<Object[]> notifications = notificationRepository.findByUserId(id);
        return ResponseEntity.ok(notifications);
    }

    @LogService
    public ResponseEntity<?> getUserNotificationsWithStatus(Long id) {
        List<Object[]> notifications = notificationRepository.findByUserIdAndStatus(id);
        return ResponseEntity.ok(notifications);
    }

}
