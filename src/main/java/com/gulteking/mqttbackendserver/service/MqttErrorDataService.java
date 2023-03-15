package com.gulteking.mqttbackendserver.service;

import com.gulteking.mqttbackendserver.entity.MqttErrorData;

import java.util.List;
import java.util.Optional;

public interface MqttErrorDataService {
    List<MqttErrorData> findAll();

    Optional<MqttErrorData> findById(Long id);

    MqttErrorData save(MqttErrorData mqttErrorData);

    MqttErrorData update(MqttErrorData mqttErrorData);

    List<MqttErrorData> findLastRecord();

    List<MqttErrorData> findLastRecordsByTopic(String topicName);
}
