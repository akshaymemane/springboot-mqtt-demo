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
@Table(name = "mqtt_topics")
public class MqttTopics {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "mt_id")
    private Long mtId;

    @Column(name = "mt_name")
    private String mtName;

}
