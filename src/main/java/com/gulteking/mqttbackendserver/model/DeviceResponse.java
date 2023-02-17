package com.gulteking.mqttbackendserver.model;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DeviceResponse {
    private String deviceSerialId;
    private String subscriberTopic;
    private String publisherUrl;
    private String encryptionKey;
}
