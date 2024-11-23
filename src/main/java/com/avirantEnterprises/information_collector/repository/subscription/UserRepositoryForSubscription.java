package com.avirantEnterprises.information_collector.repository.subscription;

import com.avirantEnterprises.information_collector.model.subscription.UserForSubscripion;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepositoryForSubscription extends JpaRepository<UserForSubscripion, Long> {
}
