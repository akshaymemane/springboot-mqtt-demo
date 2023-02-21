package com.gulteking.mqttbackendserver.serviceimpl;

import com.google.gson.Gson;
import com.gulteking.mqttbackendserver.entity.Device;
import com.gulteking.mqttbackendserver.model.DeviceResponse;
import com.gulteking.mqttbackendserver.model.JsonResponse;
import com.gulteking.mqttbackendserver.model.MqttTopicSubscribe;
import com.gulteking.mqttbackendserver.repository.DeviceRepository;
import com.gulteking.mqttbackendserver.service.DeviceService;
import com.gulteking.mqttbackendserver.utils.constants.StringConstants;
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
        //TODO Need to use findAllByDeviceIsActiveTrue in future
        List<Device> deviceList = deviceRepository.findAll();
        try {
            if (subscribeUnsubscribe) {
                for (Device mqttTopics : deviceList) {
                    mqttSubscriber.unsubscribeMessage(mqttTopics.getDevicePublisherUrl());
                    mqttSubscriber.subscribeMessage(mqttTopics.getDevicePublisherUrl());
                    mqttSubscriber.unsubscribeMessage(mqttTopics.getDeviceSerialId() + StringConstants.TEXT_TOPIC_PUBLISHING_KEY);
                    mqttSubscriber.subscribeMessage(mqttTopics.getDeviceSerialId() + StringConstants.TEXT_TOPIC_PUBLISHING_KEY);
                }
            } else {
                for (Device mqttTopics : deviceList) {
                    mqttSubscriber.unsubscribeMessage(mqttTopics.getDevicePublisherUrl());
                    mqttSubscriber.unsubscribeMessage(mqttTopics.getDeviceSerialId() + StringConstants.TEXT_TOPIC_PUBLISHING_KEY);
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
        Optional<Device> device = deviceRepository.findById(messagePublishModel.getDeviceId());
        if (device.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(JsonResponse.builder()
                    .message("Something went wrong while subscribing Topic!!!")
                    .status(HttpStatus.BAD_REQUEST)
                    .statusCode(HttpStatus.BAD_REQUEST.value())
                    .build());
        }
        if (messagePublishModel.getIsSubscribed()) {
            mqttSubscriber.unsubscribeMessage(device.get().getDevicePublisherUrl());
            mqttSubscriber.subscribeMessage(device.get().getDevicePublisherUrl());
            mqttSubscriber.unsubscribeMessage(device.get().getDeviceSerialId() + StringConstants.TEXT_TOPIC_PUBLISHING_KEY);
            mqttSubscriber.subscribeMessage(device.get().getDeviceSerialId() + StringConstants.TEXT_TOPIC_PUBLISHING_KEY);
        } else {
            mqttSubscriber.unsubscribeMessage(device.get().getDevicePublisherUrl());
            mqttSubscriber.unsubscribeMessage(device.get().getDeviceSerialId() + StringConstants.TEXT_TOPIC_PUBLISHING_KEY);
        }
        return ResponseEntity.status(HttpStatus.OK).body(JsonResponse.builder()
                .message("Topic Subscribed successfully !!!")
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .build());
    }

    @Override
    public ResponseEntity<Object> publishDataToInactiveDevices() {
        //TODO Need to remove findAll Method with in-active false 
        List<Device> deviceList = deviceRepository.findAll();
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
                    .PT(device.getDevicePublisherUrl())
                    .ST(device.getDeviceSubscriberUrl())
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

    @Override
    public Optional<Device> findByDeviceSerialId(String mqttTopic) {
        return deviceRepository.findByDeviceSerialId(mqttTopic);
    }
}
