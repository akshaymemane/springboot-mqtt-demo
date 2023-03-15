package com.gulteking.mqttbackendserver.controller;

import com.gulteking.mqttbackendserver.entity.MqttErrorData;
import com.gulteking.mqttbackendserver.model.JsonResponse;
import com.gulteking.mqttbackendserver.service.MqttErrorDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/mqttErrorData")
public class MqttErrorDataController {

    @Autowired
    MqttErrorDataService mqttErrorDataService;


    @GetMapping
    public ResponseEntity<Object> getMqttErrorData() {
        List<MqttErrorData> mqttErrorDataList = mqttErrorDataService.findLastRecord();
        if (mqttErrorDataList.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(JsonResponse.builder()
                    .message("Mqtt Error data not found!!!")
                    .status(HttpStatus.NOT_FOUND)
                    .statusCode(HttpStatus.NOT_FOUND.value())
                    .build());
        }
        return ResponseEntity.status(HttpStatus.OK).body(JsonResponse.builder()
                .message("Mqtt Error data fetched successfully !!!")
                .data(mqttErrorDataList)
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .build());
    }
}
