package com.tshortly.notification.dlq.repository;

import com.tshortly.notification.dlq.entity.FailedEventEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FailedEventRepository extends JpaRepository<FailedEventEntity, Long> {
}