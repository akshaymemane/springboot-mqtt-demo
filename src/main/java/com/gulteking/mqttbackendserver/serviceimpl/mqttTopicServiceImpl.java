package com.gulteking.mqttbackendserver.serviceimpl;

import com.gulteking.mqttbackendserver.entity.MqttTopics;
import com.gulteking.mqttbackendserver.repository.MqttTopicsRepository;
import com.gulteking.mqttbackendserver.service.MqttTopicsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class mqttTopicServiceImpl implements MqttTopicsService {
    @Autowired
    MqttTopicsRepository mqttTopicsRepository;

    @Autowired
    MqttSubscriberImpl mqttSubscriber;

    @Override
    public List<MqttTopics> findAll() {
        return mqttTopicsRepository.findAll();
    }

    @Override
    public Optional<MqttTopics> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public MqttTopics save(MqttTopics mqttTopics) {
        MqttTopics createdTopic = mqttTopicsRepository.save(mqttTopics);
        mqttSubscriber.subscribeMessage(createdTopic.getMtName());
        return createdTopic;

    }

    @Override
    public MqttTopics update(MqttTopics mqttTopics) {
        return mqttTopicsRepository.save(mqttTopics);
    }
}
