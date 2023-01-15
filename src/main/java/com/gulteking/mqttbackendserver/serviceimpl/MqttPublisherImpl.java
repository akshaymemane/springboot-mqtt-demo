package com.gulteking.mqttbackendserver.serviceimpl;

import com.gulteking.mqttbackendserver.config.MqttConfig;
import com.gulteking.mqttbackendserver.exceptions.MqttException;
import org.eclipse.paho.client.mqttv3.*;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class MqttPublisherImpl extends MqttConfig implements MqttCallback {
    private String brokerUrl = null;
    final private String colon = ":";
    final private String clientId = UUID.randomUUID().toString();

    private MqttClient mqttClient = null;
    private MqttConnectOptions connectionOptions = null;
    private MemoryPersistence persistence = null;
    private static final Logger logger = LoggerFactory.getLogger(MqttPublisherImpl.class);


    private MqttPublisherImpl() {
        this.config();
    }

    private MqttPublisherImpl(String broker, Integer port, Boolean ssl, Boolean withUserNamePass) {
        this.config(broker, port, ssl, withUserNamePass);
    }

    public static MqttPublisherImpl getInstance() {
        return new MqttPublisherImpl();
    }


    public static MqttPublisherImpl getInstance(String broker, Integer port, Boolean ssl, Boolean withUserNamePass) {
        return new MqttPublisherImpl(broker, port, ssl, withUserNamePass);
    }

    public void publishMessage(String topic, String message) {
        try {
            MqttMessage mqttmessage = new MqttMessage(message.getBytes());
            mqttmessage.setQos(this.qos);
            mqttmessage.setRetained(false);
            this.mqttClient.publish(topic, mqttmessage);
        } catch (MqttException me) {
            logger.error("ERROR", me);
        } catch (MqttPersistenceException e) {
            throw new RuntimeException(e);
        } catch (org.eclipse.paho.client.mqttv3.MqttException e) {
            throw new RuntimeException(e);
        }
    }

    public void disconnect() {
        try {
            this.mqttClient.disconnect();
        } catch (MqttException me) {
            logger.error("ERROR", me);
        } catch (org.eclipse.paho.client.mqttv3.MqttException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void connectionLost(Throwable arg0) {
        logger.info("Connection Lost");

    }

    @Override
    public void deliveryComplete(IMqttDeliveryToken arg0) {
        logger.info("delivery completed");

    }


    @Override
    public void messageArrived(String arg0, MqttMessage arg1) throws Exception {
        // Leave it blank for Publisher

    }

    @Override
    protected void config(String broker, Integer port, Boolean ssl, Boolean withUserNamePass) {
        // Like we did in MqttSubscribe
        logger.info("Inside Parameter with parameter Config");
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
        } catch (org.eclipse.paho.client.mqttv3.MqttException me) {
            // throw new Exception(me);
        }
    }

    @Override
    protected void config() {
        logger.info("Inside Config");
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
        } catch (org.eclipse.paho.client.mqttv3.MqttException me) {
            // throw new com.bms.exceptions.MqttException("Not Connected");
        }
        // Like we did in MqttSubscribe
    }


}