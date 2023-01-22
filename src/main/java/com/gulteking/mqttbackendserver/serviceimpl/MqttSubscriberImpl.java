package com.gulteking.mqttbackendserver.serviceimpl;

import com.google.gson.Gson;
import com.gulteking.mqttbackendserver.config.MqttConfig;
import com.gulteking.mqttbackendserver.entity.MqttData;
import com.gulteking.mqttbackendserver.entity.MqttTopics;
import com.gulteking.mqttbackendserver.model.MqttResponse;
import com.gulteking.mqttbackendserver.service.MqttDataService;
import com.gulteking.mqttbackendserver.service.MqttTopicsService;
import org.eclipse.paho.client.mqttv3.*;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.util.Optional;
import java.util.UUID;

@Component
public class MqttSubscriberImpl extends MqttConfig implements MqttCallback {

    @Autowired
    MqttDataService mqttDataService;

    @Autowired
    MqttTopicsService mqttTopicsService;

    private String brokerUrl = null;
    final private String colon = ":";
    final private String clientId = UUID.randomUUID().toString();

    private MqttClient mqttClient = null;
    private MqttConnectOptions connectionOptions = null;
    private MemoryPersistence persistence = null;
    private static final Logger logger = LoggerFactory.getLogger(MqttSubscriberImpl.class);

    public MqttSubscriberImpl() {
        logger.info("I am MqttSub impl");
        this.config();
    }


    @Override
    protected void config(String broker, Integer port, Boolean ssl, Boolean withUserNamePass) {
        logger.info("Inside Parameter Config");
        String protocal = this.TCP;
        this.brokerUrl = protocal + this.broker + colon + port;
        this.persistence = new MemoryPersistence();
        this.connectionOptions = new MqttConnectOptions();
        try {
            this.mqttClient = new MqttClient(brokerUrl, clientId, persistence);
            this.connectionOptions.setCleanSession(true);
            this.connectionOptions.setPassword(this.password.toCharArray());
            this.connectionOptions.setUserName(this.userName);
            this.mqttClient.connect(this.connectionOptions);
            this.mqttClient.setCallback(this);
        } catch (MqttException me) {
            // throw new Exception(me);
        }
    }

    @Override
    protected void config() {
        logger.info("Inside Config with parameter");
        this.brokerUrl = this.TCP + this.broker + colon + this.port;
        this.persistence = new MemoryPersistence();
        this.connectionOptions = new MqttConnectOptions();
        try {
            this.mqttClient = new MqttClient(brokerUrl, clientId, persistence);
            this.connectionOptions.setCleanSession(true);
            this.connectionOptions.setPassword(this.password.toCharArray());
            this.connectionOptions.setUserName(this.userName);
            this.mqttClient.connect(this.connectionOptions);
            this.mqttClient.setCallback(this);
        } catch (MqttException me) {
            me.getMessage();
        }
    }

    @Override
    public void connectionLost(Throwable throwable) {
        logger.info("Connection Lost" + throwable);
        this.config();
    }


    @Override
    public void messageArrived(String mqttTopic, MqttMessage mqttMessage) throws Exception {
        String time = new Timestamp(System.currentTimeMillis()).toString();
        System.out.println("***********************************************************************");
        String data = new String(mqttMessage.getPayload());
        if (data != null || data != " ") {
            System.out.println("Message Arrived at Time: " + time + "  Topic: " + mqttTopic + "  Message: "
                    + new String(mqttMessage.getPayload()));
            MqttData mqttData = MqttData.builder()
                    .mqttDataTopic(mqttTopic)
                    .mqttDataSyncedData(data)
                    .build();
            mqttDataService.save(mqttData);

            Optional<MqttTopics> topicData = mqttTopicsService.findByTopicName(mqttTopic);
            if (!topicData.isEmpty()) {
                MqttResponse mqttResponse = MqttResponse.builder()
                        .ST("OK").PID("1234")
                        .build();
                System.out.println("Rishabh Kukkar " + mqttResponse.toString());
                publishMessage(topicData.get().getMtPublisherName(), new Gson().toJson(mqttResponse));
            }
        }
        System.out.println("***********************************************************************");
    }

    @Override
    public void deliveryComplete(IMqttDeliveryToken iMqttDeliveryToken) {

    }

    public void subscribeMessage(String topic) {
        try {
            this.mqttClient.subscribe(topic, this.qos);
        } catch (MqttException me) {
            System.out.println("Not able to Read Topic  " + topic);
            // me.printStackTrace();
        }
    }

    public void publishMessage(String topic, String message) {
        try {
            MqttMessage mqttmessage = new MqttMessage(message.getBytes());
            mqttmessage.setQos(this.qos);
            mqttmessage.setRetained(false);
            this.mqttClient.publish(topic, mqttmessage);
        } catch (com.gulteking.mqttbackendserver.exceptions.MqttException me) {
            logger.error("ERROR", me);
        } catch (MqttPersistenceException e) {
            throw new RuntimeException(e);
        } catch (org.eclipse.paho.client.mqttv3.MqttException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean unsubscribeMessage(String topic) {
        try {
            this.mqttClient.unsubscribe(topic);
            return true;
        } catch (MqttException me) {
            System.out.println("Not able to Read Topic  " + topic);
            return false;
            // me.printStackTrace();
        }
    }

    public void disconnect() {
        try {
            this.mqttClient.disconnect();
        } catch (MqttException me) {
            logger.error("ERROR", me);
        }
    }
}
