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
@Table(name = "mqtt_data")
public class MqttData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "mqtt_data_id")
    private Long mqttDataId;

    @Column(name = "mqtt_data_topic")
    private String mqttDataTopic;

    @Column(name = "mqtt_data_synced_data")
    private String mqttDataSyncedData;
}
