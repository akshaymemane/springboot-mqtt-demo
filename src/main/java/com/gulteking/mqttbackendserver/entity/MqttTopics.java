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
@Table(name = "mqtt_topics")
public class MqttTopics {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "mt_id")
    private Long mtId;

    @Column(name = "mt_name")
    private String mtName;

    @Column(name = "mt_publisher_name")
    private String mtPublisherName;

    @CreationTimestamp
    @Column(name = "mt_created_date")
    private Date mtCreatedDate;

}
