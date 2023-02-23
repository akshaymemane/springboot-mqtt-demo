package com.gulteking.mqttbackendserver.service;

import com.gulteking.mqttbackendserver.entity.Device;
import com.gulteking.mqttbackendserver.model.MqttTopicSubscribe;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

public interface DeviceService {
    ResponseEntity<Object> subscribeToAllTopic(Boolean subscribeUnsubscribe);

    ResponseEntity<Object> subscribeTopic(MqttTopicSubscribe messagePublishModel);

    ResponseEntity<Object> publishDataToInactiveDevices();

    Optional<Device> findByTopicName(String mqttTopic);

    Optional<Device> findByDeviceSerialId(String serialId);
}