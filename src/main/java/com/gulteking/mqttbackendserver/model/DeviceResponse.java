package com.gulteking.mqttbackendserver.model;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@Builder
public class DeviceResponse {
    private String deviceSerialId;
    private String deviceSubscriberUrl;
    private String devicePublisherUrl;
    private String deviceDataEncryptionKey;
}
