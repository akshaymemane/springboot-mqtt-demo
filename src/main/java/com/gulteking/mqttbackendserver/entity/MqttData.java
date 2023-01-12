package com.gulteking.mqttbackendserver.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;

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

    @CreationTimestamp
    @Column(name = "mqtt_data_created_date")
    private Date mqttDataCreatedDate;
}
