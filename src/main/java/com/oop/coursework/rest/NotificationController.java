package com.oop.coursework.rest;

import com.oop.coursework.annotation.LogController;
import com.oop.coursework.model.Notification;
import com.oop.coursework.services.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class NotificationController {

    private final NotificationService notificationService;

    @Autowired
    public NotificationController(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @LogController
    @PostMapping("notification")
    public ResponseEntity<?> createNewNotification(@RequestBody Notification notification){
        notificationService.createNewNotification(notification);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @LogController
    @GetMapping("notification")
    public ResponseEntity<?> getNotificationById(@RequestParam(value = "id") Long id) {
        return notificationService.getNotificationById(id);
    }

    @LogController
    @PutMapping("notification")
    public ResponseEntity<?> updateNotification(@RequestBody Notification newNotificationData) {
        return notificationService.updateNotification(newNotificationData.getId(), newNotificationData);
    }

    @LogController
    @DeleteMapping("notification")
    public ResponseEntity<?> deleteNotification(@RequestParam(value = "id") Long id) {
        return notificationService.deleteNotification(id);
    }

    @LogController
    @GetMapping("notification/get-user-notifications")
    public ResponseEntity<?> getUserNotifications(@RequestParam(value = "id") Long id) {
        return notificationService.getUserNotifications(id);
    }

    @LogController
    @GetMapping("notification/get-unread-user-notifications")
    public ResponseEntity<?> getUserNotificationsWithStatus(@RequestParam(value = "id") Long id) {
        return notificationService.getUserNotificationsWithStatus(id);
    }

}
