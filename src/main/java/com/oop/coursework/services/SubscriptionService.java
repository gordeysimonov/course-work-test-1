package com.oop.coursework.services;

import com.oop.coursework.model.Subscription;
import com.oop.coursework.repo.SubscriptionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class SubscriptionService {

    private final SubscriptionRepo subscriptionRepository;

    @Autowired
    public SubscriptionService(SubscriptionRepo subscriptionRepository) {
        this.subscriptionRepository = subscriptionRepository;
    }

    public void createNewSubscription(Subscription subscription) {
        subscription.setSubscriptionDate(LocalDateTime.now());
        subscriptionRepository.save(subscription);
    }

    public ResponseEntity<?> getSubscriptionById(long id) {
        Optional<Subscription> optionalSubscription = subscriptionRepository.findById(id);
        if (optionalSubscription.isPresent()) {
            return ResponseEntity.ok(optionalSubscription.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    public ResponseEntity<?> updateSubscription(long id, Subscription newSubscriptionData) {
        Optional<Subscription> optionalSubscription = subscriptionRepository.findById(id);
        if (optionalSubscription.isPresent()) {
            Subscription existingSubscription = optionalSubscription.get();
            if(newSubscriptionData.getSubscribedTo() != null) {
                existingSubscription.setSubscribedTo(newSubscriptionData.getSubscribedTo());
            }
            if(newSubscriptionData.getSubscriber() != null) {
                existingSubscription.setSubscriber(newSubscriptionData.getSubscriber());
            }
            if(newSubscriptionData.getSubscriptionDate() != null) {
                existingSubscription.setSubscriptionDate(newSubscriptionData.getSubscriptionDate());
            }
            subscriptionRepository.save(existingSubscription);
            return ResponseEntity.ok(existingSubscription);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    public ResponseEntity<?> deleteSubscription(long id) {
        Optional<Subscription> optionalSubscription = subscriptionRepository.findById(id);
        if (optionalSubscription.isPresent()) {
            subscriptionRepository.deleteById(id);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    public ResponseEntity<?> getSubscriptions(Long id) {
        List<Object[]> subscriptions = subscriptionRepository.findSubscriptions(id);
        return ResponseEntity.ok(subscriptions);
    }

    public ResponseEntity<?> getSubscribers(Long id) {
        List<Object[]> subscriptions = subscriptionRepository.findSubscribers(id);
        return ResponseEntity.ok(subscriptions);
    }

}
