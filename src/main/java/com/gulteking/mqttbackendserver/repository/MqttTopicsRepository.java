package com.gulteking.mqttbackendserver.repository;

import com.gulteking.mqttbackendserver.entity.MqttTopics;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MqttTopicsRepository extends JpaRepository<MqttTopics, Long> {
    Optional<MqttTopics> findByMtName(String name);
}

