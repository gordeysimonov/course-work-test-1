package com.oop.coursework.services;

import com.oop.coursework.model.Subscription;
import com.oop.coursework.repo.SubscriptionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SubscriptionService {

    private SubscriptionRepo subscriptionRepository;

    @Autowired
    public SubscriptionService(SubscriptionRepo subscriptionRepository) {
        this.subscriptionRepository = subscriptionRepository;
    }

    public Subscription createNewSubscription(Subscription subscription) {
        return subscriptionRepository.save(subscription);
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
            existingSubscription.setSubscribedId(newSubscriptionData.getSubscribedId());
            if(newSubscriptionData.getSubscriberId() != null) {
                existingSubscription.setSubscriberId(newSubscriptionData.getSubscriberId());
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

}
