package com.gulteking.mqttbackendserver.controller;

import com.gulteking.mqttbackendserver.model.JsonResponse;
import com.gulteking.mqttbackendserver.service.MqttTopicsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/publishDataToMqttDevices")
public class DefaultDataPublishingController {

    @Autowired
    MqttTopicsService mqttTopicsService;

    @GetMapping
    public ResponseEntity<Object> publishMessages() {
        Boolean isConnected = mqttTopicsService.findAllByIsConnected();
        if (isConnected != null)
            return ResponseEntity.status(HttpStatus.OK)
                    .body(JsonResponse.builder()
                            .message("Topic data published successfully !!!")
                            .status(HttpStatus.OK)
                            .statusCode(HttpStatus.OK.value())
                            .build());
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(JsonResponse.builder()
                        .message("Topic data publishing error!!!")
                        .status(HttpStatus.NOT_FOUND)
                        .statusCode(HttpStatus.NOT_FOUND.value())
                        .build());
    }
}
