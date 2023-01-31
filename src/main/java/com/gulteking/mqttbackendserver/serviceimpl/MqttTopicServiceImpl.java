package com.gulteking.mqttbackendserver.serviceimpl;

import com.google.gson.Gson;
import com.gulteking.mqttbackendserver.entity.MqttTopics;
import com.gulteking.mqttbackendserver.model.MqttTopicSubscribe;
import com.gulteking.mqttbackendserver.repository.MqttDataRepository;
import com.gulteking.mqttbackendserver.repository.MqttTopicsRepository;
import com.gulteking.mqttbackendserver.service.MqttTopicsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MqttTopicServiceImpl implements MqttTopicsService {
    @Autowired
    MqttTopicsRepository mqttTopicsRepository;

    @Autowired
    MqttSubscriberImpl mqttSubscriber;

    @Autowired
    private MqttDataRepository mqttDataRepository;

    @Override
    public List<MqttTopics> findAll() {
        return mqttTopicsRepository.findAll();
    }

    @Override
    public Boolean findAllByIsConnected(Boolean isConnected) {
        try {
            List<MqttTopics> topicsList = mqttTopicsRepository.findAll();
            for (MqttTopics mqttTopics : topicsList) {
                mqttSubscriber.publishMessage(mqttTopics.getMtSerialNumber(), new Gson().toJson(mqttTopics));
            }
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    @Override
    public Optional<MqttTopics> findById(Long id) {
        return mqttTopicsRepository.findById(id);
    }

    @Override
    public Optional<MqttTopics> findByTopicName(String topicName) {
        return mqttTopicsRepository.findByMtPublisherTopic(topicName);
    }


    @Override
    public MqttTopics save(MqttTopics mqttTopics) {
        MqttTopics createdTopic = mqttTopicsRepository.save(mqttTopics);
        mqttSubscriber.subscribeMessage(createdTopic.getMtPublisherTopic());
        return createdTopic;
    }

    @Override
    public MqttTopics update(MqttTopics mqttTopics) {
        return mqttTopicsRepository.save(mqttTopics);
    }

    @Override
    public Boolean subscribeTopic(MqttTopicSubscribe messagePublishModel) {
        Optional<MqttTopics> topics = mqttTopicsRepository.findById(messagePublishModel.getTopicId());
        if (topics.isEmpty()) {
            return false;
        }
        if (messagePublishModel.getSubscribe()) {
            mqttSubscriber.unsubscribeMessage(topics.get().getMtPublisherTopic());
            mqttSubscriber.subscribeMessage(topics.get().getMtPublisherTopic());
        } else {
            mqttSubscriber.unsubscribeMessage(topics.get().getMtPublisherTopic());
        }
        return true;
    }

    @Override
    public Boolean subscribeToAllTopic(Boolean subscribeUnsubscribe) {
        List<MqttTopics> topicsList = mqttTopicsRepository.findAll();
        try {
            if (subscribeUnsubscribe) {
                for (MqttTopics mqttTopics : topicsList) {
                    mqttSubscriber.unsubscribeMessage(mqttTopics.getMtPublisherTopic());
                    mqttSubscriber.subscribeMessage(mqttTopics.getMtPublisherTopic());
                }
            } else {
                for (MqttTopics mqttTopics : topicsList) {
                    mqttSubscriber.unsubscribeMessage(mqttTopics.getMtPublisherTopic());
                }
            }
            return true;
        } catch (Exception exception) {
            return false;
        }
    }
}
