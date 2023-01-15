package com.gulteking.mqttbackendserver.service;

import com.gulteking.mqttbackendserver.entity.MqttData;

import java.util.List;
import java.util.Optional;

public interface MqttDataService {
    List<MqttData> findAll();

    Optional<MqttData> findById(Long id);

    MqttData save(MqttData mqttData);

    MqttData update(MqttData mqttData);

    MqttData findLastRecord();
}
