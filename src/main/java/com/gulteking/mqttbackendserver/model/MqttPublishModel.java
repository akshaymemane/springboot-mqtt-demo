package com.gulteking.mqttbackendserver.model;


import javax.validation.constraints.NotNull;

public class MqttPublishModel {

    private String topic;

    @NotNull
    private String message;

    @NotNull
    private Boolean retained;

    @NotNull
    private Integer qos;


    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Boolean getRetained() {
        return retained;
    }

    public void setRetained(Boolean retained) {
        this.retained = retained;
    }

    public Integer getQos() {
        return qos;
    }

    public void setQos(Integer qos) {
        this.qos = qos;
    }
}
