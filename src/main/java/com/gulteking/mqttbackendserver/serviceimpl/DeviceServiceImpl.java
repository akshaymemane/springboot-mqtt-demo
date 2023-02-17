package com.gulteking.mqttbackendserver.serviceimpl;

import com.google.gson.Gson;
import com.gulteking.mqttbackendserver.entity.Device;
import com.gulteking.mqttbackendserver.model.DeviceResponse;
import com.gulteking.mqttbackendserver.model.JsonResponse;
import com.gulteking.mqttbackendserver.model.MqttTopicSubscribe;
import com.gulteking.mqttbackendserver.repository.DeviceRepository;
import com.gulteking.mqttbackendserver.service.DeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DeviceServiceImpl implements DeviceService {
    @Autowired
    DeviceRepository deviceRepository;

    @Autowired
    MqttSubscriberImpl mqttSubscriber;

    @Autowired
    MqttPublisherImpl mqttPublisher;

    @Override
    public ResponseEntity<Object> subscribeToAllTopic(Boolean subscribeUnsubscribe) {
        List<Device> deviceList = deviceRepository.findAllByDeviceIsActiveTrue();
        try {
            if (subscribeUnsubscribe) {
                for (Device mqttTopics : deviceList) {
                    mqttSubscriber.unsubscribeMessage(mqttTopics.getDevicePublisherUrl());
                    mqttSubscriber.subscribeMessage(mqttTopics.getDevicePublisherUrl());
                }
            } else {
                for (Device mqttTopics : deviceList) {
                    mqttSubscriber.unsubscribeMessage(mqttTopics.getDevicePublisherUrl());
                }
            }
            return ResponseEntity.status(HttpStatus.OK).body(JsonResponse.builder()
                    .message("Topic Subscribed successfully !!!")
                    .status(HttpStatus.OK)
                    .statusCode(HttpStatus.OK.value())
                    .build());
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(JsonResponse.builder()
                    .message("Something went wrong while subscribing Topic!!!")
                    .status(HttpStatus.BAD_REQUEST)
                    .statusCode(HttpStatus.BAD_REQUEST.value())
                    .build());
        }
    }

    @Override
    public ResponseEntity<Object> subscribeTopic(MqttTopicSubscribe messagePublishModel) {
        Optional<Device> topics = deviceRepository.findById(messagePublishModel.getTopicId());
        if (topics.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(JsonResponse.builder()
                    .message("Something went wrong while subscribing Topic!!!")
                    .status(HttpStatus.BAD_REQUEST)
                    .statusCode(HttpStatus.BAD_REQUEST.value())
                    .build());
        }
        if (messagePublishModel.getSubscribe()) {
            mqttSubscriber.unsubscribeMessage(topics.get().getDevicePublisherUrl());
            mqttSubscriber.subscribeMessage(topics.get().getDevicePublisherUrl());
        } else {
            mqttSubscriber.unsubscribeMessage(topics.get().getDevicePublisherUrl());
        }
        return ResponseEntity.status(HttpStatus.OK).body(JsonResponse.builder()
                .message("Topic Subscribed successfully !!!")
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .build());
    }

    @Override
    public ResponseEntity<Object> publishDataToInactiveDevices() {
        List<Device> deviceList = deviceRepository.findAllByDeviceIsActiveFalse();
        if (deviceList.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(JsonResponse.builder()
                            .message("No in-active Devices found to publish Data!!!")
                            .status(HttpStatus.NOT_FOUND)
                            .statusCode(HttpStatus.NOT_FOUND.value())
                            .build());
        }
        for (Device device : deviceList) {
            DeviceResponse deviceResponse = DeviceResponse.builder()
                    .deviceSerialId(device.getDeviceSerialId())
                    .publisherUrl(device.getDevicePublisherUrl())
                    .subscriberTopic(device.getDeviceSubscriberUrl())
                    .build();
            mqttPublisher.publishMessage(device.getDeviceSerialId(), new Gson().toJson(deviceResponse));
        }
        return ResponseEntity.status(HttpStatus.OK)
                .body(JsonResponse.builder()
                        .message("Topic data published successfully !!!")
                        .status(HttpStatus.OK)
                        .statusCode(HttpStatus.OK.value())
                        .build());
    }

    @Override
    public Optional<Device> findByTopicName(String mqttTopic) {
        return deviceRepository.findByDevicePublisherUrl(mqttTopic);
    }
}
