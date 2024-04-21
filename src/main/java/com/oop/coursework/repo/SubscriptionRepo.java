package com.oop.coursework.repo;

import com.oop.coursework.model.Subscription;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubscriptionRepo extends JpaRepository<Subscription, Long> {
}
