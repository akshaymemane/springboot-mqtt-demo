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

    @Column(name = "mt_serial_number")
    private String mtSerialNumber;

    @Column(name = "mt_subscriber_topic")
    private String mtSubscriberTopic;

    @Column(name = "mt_publisher_topic")
    private String mtPublisherTopic;

    @Column(name = "mt_is_device_connected")
    private Boolean mtIsDeviceConnected;

    @CreationTimestamp
    @Column(name = "mt_created_date")
    private Date mtCreatedDate;

}
