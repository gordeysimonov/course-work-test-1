package com.oop.coursework.rest;

import com.oop.coursework.model.Subscription;
import com.oop.coursework.services.SubscriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class SubscriptionController {

    private final SubscriptionService subscriptionService;

    @Autowired
    public SubscriptionController(SubscriptionService subscriptionService) {
        this.subscriptionService = subscriptionService;
    }

    @PostMapping("subscription")
    public ResponseEntity<?> createNewSubscription(@RequestBody Subscription subscription){
        subscriptionService.createNewSubscription(subscription);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("subscription")
    public ResponseEntity<?> getSubscriptionById(@RequestParam(value = "id") long id) {
        return subscriptionService.getSubscriptionById(id);
    }

    @PutMapping("subscription")
    public ResponseEntity<?> updateSubscription(@RequestBody Subscription newSubscriptionData) {
        return subscriptionService.updateSubscription(newSubscriptionData.getId(), newSubscriptionData);
    }

    @DeleteMapping("subscription")
    public ResponseEntity<?> deleteSubscription(@RequestParam(value = "id") long id) {
        return subscriptionService.deleteSubscription(id);
    }

    @GetMapping("subscription/get-subscriptions")
    public ResponseEntity<?> getSubscriptions(@RequestParam(value = "id") long id) {
        return subscriptionService.getSubscriptions(id);
    }

    @GetMapping("subscription/get-subscribers")
    public ResponseEntity<?> getSubscribers(@RequestParam(value = "id") long id) {
        return subscriptionService.getSubscribers(id);
    }

}
