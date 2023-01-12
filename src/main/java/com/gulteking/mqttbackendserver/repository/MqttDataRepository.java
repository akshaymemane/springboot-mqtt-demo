package com.gulteking.mqttbackendserver.repository;

import com.gulteking.mqttbackendserver.entity.MqttData;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MqttDataRepository extends JpaRepository<MqttData, Long> {
}
