package com.gulteking.mqttbackendserver.controller;

import com.gulteking.mqttbackendserver.entity.MqttData;
import com.gulteking.mqttbackendserver.model.JsonResponse;
import com.gulteking.mqttbackendserver.service.MqttDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/mqttData")
public class MqttDataController {

    @Autowired
    MqttDataService mqttDataService;

    @GetMapping
    public ResponseEntity<Object> publishMessage(
            @RequestParam(required = false, defaultValue = "") String topicName
    ) {
        List<MqttData> mqttData = new ArrayList<>();
        if (topicName.isBlank())
            mqttData = mqttDataService.findLastRecord();
        else
            mqttData = mqttDataService.findLastRecordsByTopic(topicName);
        if (!mqttData.isEmpty())
            return ResponseEntity.status(HttpStatus.OK).body(JsonResponse.builder()
                    .message("Topic data fetched successfully !!!")
                    .data(mqttData)
                    .status(HttpStatus.OK)
                    .statusCode(HttpStatus.OK.value())
                    .build());
        else
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(JsonResponse.builder()
                            .message("Topic data Not Found!!!")
                            .status(HttpStatus.NOT_FOUND)
                            .statusCode(HttpStatus.NOT_FOUND.value())
                            .build());
    }
}
