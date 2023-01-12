package com.gulteking.mqttbackendserver.controller;

import com.gulteking.mqttbackendserver.entity.MqttTopics;
import com.gulteking.mqttbackendserver.repository.MqttTopicsRepository;
import com.gulteking.mqttbackendserver.service.MqttTopicsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/mqttTopics")
public class MqttTopicController {

    @Autowired
    MqttTopicsService mqttTopicsService;

    @PostMapping
    public void publishMessage(@RequestBody MqttTopics messagePublishModel) {
        mqttTopicsService.save(messagePublishModel);
    }
}
