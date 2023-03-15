package com.gulteking.mqttbackendserver.repository;

import com.gulteking.mqttbackendserver.entity.MqttData;
import com.gulteking.mqttbackendserver.entity.MqttErrorData;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MqttErrorDataRepository extends JpaRepository<MqttErrorData, Long> {

    List<MqttErrorData> findAllByMqttErrorDataTopic(String topicName);

    Page<MqttErrorData> findAllByMqttErrorDataTopic(String topic, Pageable pageable);
}
