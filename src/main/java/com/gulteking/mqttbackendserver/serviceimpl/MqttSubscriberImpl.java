package com.gulteking.mqttbackendserver.serviceimpl;

import com.gulteking.mqttbackendserver.config.MqttConfig;
import org.eclipse.paho.client.mqttv3.*;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.util.UUID;

@Component
public class MqttSubscriberImpl extends MqttConfig implements MqttCallback {

    private static final String fota_fetch_record = "fota_fetch_record";
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
            // throw new com.bms.exceptions.MqttException("Not Connected");
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
        System.out.println("Message Arrived at Time: " + time + "  Topic: " + mqttTopic + "  Message: "
                + new String(mqttMessage.getPayload()));
        System.out.println("***********************************************************************");
    }

    @Override
    public void deliveryComplete(IMqttDeliveryToken iMqttDeliveryToken) {

    }

    public void subscribeMessage(String topic) {
        try {
            logger.info("Rishabh Kukkar Sub1 " + topic);
            this.mqttClient.subscribe(topic, this.qos);
            logger.info("Rishabh Kukkar Sub2 " + topic);

        } catch (MqttException me) {
            System.out.println("Not able to Read Topic  " + topic);
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
