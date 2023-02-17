package com.gulteking.mqttbackendserver.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.gulteking.mqttbackendserver.utils.constants.StringConstants;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "device")
public class Device {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "device_id")
    private Long deviceId;

    @Column(name = "device_type_id")
    private Long deviceTypeId;

    @Column(name = "device_serial_id")
    private String deviceSerialId;

    @Column(name = "device_subscriber_url")
    private String deviceSubscriberUrl = StringConstants.TEXT_EMPTY;

    @Column(name = "device_publisher_url")
    private String devicePublisherUrl = StringConstants.TEXT_EMPTY;

    @Column(name = "device_data_encryption_key")
    private String deviceDataEncryptionKey = StringConstants.TEXT_EMPTY;

    @Column(name = "device_is_active")
    private Boolean deviceIsActive = StringConstants.TRUE;

    @CreationTimestamp
    @Column(name = "device_created_date")
    private Date deviceCreatedDate;

    @UpdateTimestamp
    @Column(name = "device_last_updated_date")
    private Date deviceLastUpdatedDate;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "device_type_id", insertable = false, updatable = false)
    @JsonIgnoreProperties("deviceList")
    private DeviceType deviceType;
}
