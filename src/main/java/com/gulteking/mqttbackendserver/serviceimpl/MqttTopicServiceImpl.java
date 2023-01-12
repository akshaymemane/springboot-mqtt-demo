package com.gulteking.mqttbackendserver.serviceimpl;

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

    @Override
    public Boolean subscribeTopic(MqttTopicSubscribe messagePublishModel) {
        Optional<MqttTopics> topics = mqttTopicsRepository.findById(messagePublishModel.getTopicId());
        if (topics.isEmpty()) {
            return false;
        }
        if (messagePublishModel.getSubscribe()) {
            Boolean isSubscribed = mqttSubscriber.unsubscribeMessage(topics.get().getMtName());
            mqttSubscriber.subscribeMessage(topics.get().getMtName());
        } else {
            mqttSubscriber.unsubscribeMessage(topics.get().getMtName());
        }
        return true;
    }

    @Override
    public Boolean subscribeToAllTopic(Boolean subscribeUnsubscribe) {
        List<MqttTopics> topicsList = mqttTopicsRepository.findAll();
        try {
            if (subscribeUnsubscribe) {
                for (MqttTopics mqttTopics : topicsList) {
                    mqttSubscriber.unsubscribeMessage(mqttTopics.getMtName());
                    mqttSubscriber.subscribeMessage(mqttTopics.getMtName());
                }
            } else {
                for (MqttTopics mqttTopics : topicsList) {
                    mqttSubscriber.unsubscribeMessage(mqttTopics.getMtName());
                }
            }
            return true;
        } catch (Exception exception) {
            return false;
        }
    }
}
