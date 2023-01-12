package com.gulteking.mqttbackendserver.repository;

import com.gulteking.mqttbackendserver.entity.MqttTopics;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MqttTopicsRepository extends JpaRepository<MqttTopics, Long> {
}

