package com.oop.coursework.repo;

import com.oop.coursework.model.Subscription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SubscriptionRepo extends JpaRepository<Subscription, Long> {

    @Query("SELECT json_build_object(" +
            "'id', s.id, " +
            "'subscriptionDate', s.subscriptionDate, " +
            "'subscriber', s.subscriber, " +
            "'subscribedTo', s.subscribedTo " +
            ") " +
            "FROM Subscription s WHERE s.subscriber.id = :userId")
    List<Object[]> findSubscriptions(@Param("userId") Long userId);

    @Query("SELECT json_build_object(" +
            "'id', s.id, " +
            "'subscriptionDate', s.subscriptionDate, " +
            "'subscriber', s.subscriber, " +
            "'subscribedTo', s.subscribedTo " +
            ") " +
            "FROM Subscription s WHERE s.subscribedTo.id = :userId")
    List<Object[]> findSubscribers(@Param("userId") Long userId);

}
