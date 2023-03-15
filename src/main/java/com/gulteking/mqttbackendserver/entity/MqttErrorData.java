package com.gulteking.mqttbackendserver.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "mqtt_error_data")
public class MqttErrorData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "mqtt_error_data_id")
    private Long mqttErrorDataId;

    @Column(name = "mqtt_error_data_topic")
    private String mqttErrorDataTopic;

    @Column(name = "mqtt_error_data_synced_data", length = 2000)
    private String mqttErrorDataSyncedData;
}
