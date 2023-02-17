package com.gulteking.mqttbackendserver.controller;

import com.gulteking.mqttbackendserver.service.DeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/publishDataToMqttDevices")
public class DefaultDataPublishingController {

    @Autowired
    DeviceService deviceService;

    @GetMapping
    public ResponseEntity<Object> publishMessages() {
        return deviceService.publishDataToInactiveDevices();
    }
}
