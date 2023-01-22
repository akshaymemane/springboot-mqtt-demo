package com.gulteking.mqttbackendserver.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MqttMessageListener implements Runnable {
    @Autowired
    MqttSubscriberImpl subscriber;

    @Override
    public void run() {
        while (true) {
            subscriber.subscribeMessage("root-mqtt");
        }
    }
}