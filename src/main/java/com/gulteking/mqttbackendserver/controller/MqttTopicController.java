package com.gulteking.mqttbackendserver.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/mqttTopics")
public class MqttTopicController {


//
//    @PostMapping
//    public ResponseEntity<Object> publishMessage(@RequestBody MqttTopics messagePublishModel) {
//        MqttTopics mqttTopic = mqttTopicsService.save(messagePublishModel);
//        if (mqttTopic != null) return ResponseEntity.status(HttpStatus.CREATED)
//                .body(JsonResponse.builder()
//                .message("Topic created successfully !!!")
//                .data(mqttTopic)
//                .status(HttpStatus.CREATED)
//                .statusCode(HttpStatus.CREATED.value())
//                .build());
//        else return ResponseEntity.status(HttpStatus.BAD_REQUEST)
//                .body(JsonResponse.builder()
//                .message("Topic creation error !!!")
//                .status(HttpStatus.BAD_REQUEST)
//                .statusCode(HttpStatus.BAD_REQUEST.value())
//                .build());
//    }
//
//    @PostMapping("/toTopic")
//    public ResponseEntity<Object> subscribeTopic(@RequestBody MqttTopicSubscribe messagePublishModel) {
//        Boolean isFailed = mqttTopicsService.subscribeTopic(messagePublishModel);
//        if (isFailed) {
//            return ResponseEntity.status(HttpStatus.OK).body(JsonResponse.builder()
//                    .message("Topic Subscribed successfully !!!")
//                    .status(HttpStatus.OK)
//                    .statusCode(HttpStatus.OK.value())
//                    .build());
//        } else {
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(JsonResponse.builder()
//                    .message("Something went wrong while subscribing Topic!!!")
//                    .status(HttpStatus.BAD_REQUEST)
//                    .statusCode(HttpStatus.BAD_REQUEST.value())
//                    .build());
//        }
//    }
//
//    @PostMapping("/toAllTopic")
//    public ResponseEntity<Object> subscribeAllTopic(@RequestBody Boolean subscribeUnsubscribe) {
//        Boolean isFailed = mqttTopicsService.subscribeToAllTopic(subscribeUnsubscribe);
//
//        if (isFailed) {
//            return ResponseEntity.status(HttpStatus.OK).body(JsonResponse.builder()
//                    .message("All Topics was " + subscribeUnsubscribe + " successfully !!!")
//                    .status(HttpStatus.OK)
//                    .statusCode(HttpStatus.OK.value())
//                    .build());
//        } else {
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(JsonResponse.builder()
//                    .message("Something went wrong while subscribing Topic!!")
//                    .status(HttpStatus.BAD_REQUEST)
//                    .statusCode(HttpStatus.BAD_REQUEST.value())
//                    .build());
//        }
//    }
}
