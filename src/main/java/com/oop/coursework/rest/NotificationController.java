package com.oop.coursework.rest;

import com.oop.coursework.model.Notification;
import com.oop.coursework.services.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class NotificationController {

    private final NotificationService notificationService;

    @Autowired
    public NotificationController(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @PostMapping("notification")
    public ResponseEntity<?> createNewNotification(@RequestBody Notification notification){
        return ResponseEntity.ok(notificationService.createNewNotification(notification));
    }

    @GetMapping("notification")
    public ResponseEntity<?> getNotificationById(@RequestParam(value = "id") long id) {
        return notificationService.getNotificationById(id);
    }

    @PutMapping("notification")
    public ResponseEntity<?> updateNotification(@RequestBody Notification newNotificationData) {
        return notificationService.updateNotification(newNotificationData.getId(), newNotificationData);
    }

    @DeleteMapping("notification")
    public ResponseEntity<?> deleteNotification(@RequestParam(value = "id") long id) {
        return notificationService.deleteNotification(id);
    }

}
