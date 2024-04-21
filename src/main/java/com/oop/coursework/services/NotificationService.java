package com.oop.coursework.services;

import com.oop.coursework.model.Notification;
import com.oop.coursework.repo.NotificationRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class NotificationService {

    private NotificationRepo notificationRepository;

    @Autowired
    public NotificationService(NotificationRepo notificationRepository) {
        this.notificationRepository = notificationRepository;
    }

    public Notification createNewNotification(Notification notification) {
        return notificationRepository.save(notification);
    }

    public ResponseEntity<?> getNotificationById(long id) {
        Optional<Notification> optionalNotification = notificationRepository.findById(id);
        if (optionalNotification.isPresent()) {
            return ResponseEntity.ok(optionalNotification.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    public ResponseEntity<?> updateNotification(long id, Notification newNotificationData) {
        Optional<Notification> optionalNotification = notificationRepository.findById(id);
        if (optionalNotification.isPresent()) {
            Notification existingNotification = optionalNotification.get();
            existingNotification.setNotificationType(newNotificationData.getNotificationType());
            existingNotification.setNotificationText(newNotificationData.getNotificationText());
            existingNotification.setStatus(newNotificationData.getStatus());
            existingNotification.setDateReceiving(newNotificationData.getDateReceiving());
            if(newNotificationData.getUserId() != null) {
                existingNotification.setUserId(newNotificationData.getUserId());
            }
            notificationRepository.save(existingNotification);
            return ResponseEntity.ok(existingNotification);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    public ResponseEntity<?> deleteNotification(long id) {
        Optional<Notification> optionalNotification = notificationRepository.findById(id);
        if (optionalNotification.isPresent()) {
            notificationRepository.deleteById(id);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
