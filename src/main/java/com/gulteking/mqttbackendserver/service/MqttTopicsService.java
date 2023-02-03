package com.gulteking.mqttbackendserver.service;

import com.gulteking.mqttbackendserver.entity.MqttTopics;
import com.gulteking.mqttbackendserver.model.MqttTopicSubscribe;

import java.util.List;
import java.util.Optional;

public interface MqttTopicsService {
    List<MqttTopics> findAll();

    Boolean findAllByIsConnected();

    Optional<MqttTopics> findById(Long id);

    Optional<MqttTopics> findByTopicName(String topicName);

    MqttTopics save(MqttTopics mqttTopics);

    MqttTopics update(MqttTopics mqttTopics);

    Boolean subscribeTopic(MqttTopicSubscribe messagePublishModel);

    Boolean subscribeToAllTopic(Boolean subscribeUnsubscribe);
}
