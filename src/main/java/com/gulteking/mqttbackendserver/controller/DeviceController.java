package com.gulteking.mqttbackendserver.controller;

import com.gulteking.mqttbackendserver.model.MqttTopicSubscribe;
import com.gulteking.mqttbackendserver.service.DeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/mqtt-device")
public class DeviceController {
    @Autowired
    DeviceService deviceService;

    @PostMapping("/toTopic")
    public ResponseEntity<Object> subscribeTopic(@RequestBody MqttTopicSubscribe mqttTopicSubscribe) {
        return deviceService.subscribeTopic(mqttTopicSubscribe);
    }

    @PostMapping("/toAllTopic")
    public ResponseEntity<Object> subscribeToAllTopic(@RequestBody Boolean subscribeUnsubscribeTopic) {
        return deviceService.subscribeToAllTopic(subscribeUnsubscribeTopic);
    }
}
