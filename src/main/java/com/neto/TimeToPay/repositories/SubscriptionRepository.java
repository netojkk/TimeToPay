package com.neto.TimeToPay.repositories;

import com.neto.TimeToPay.domain.Subscription;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SubscriptionRepository extends JpaRepository<Subscription, Long> {
    List<Subscription> findByClientId(Long clientId);
    Boolean existsByNameAndClientId(String name, Long clientId);
}
