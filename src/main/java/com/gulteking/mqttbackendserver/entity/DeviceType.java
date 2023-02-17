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
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "device_type")
public class DeviceType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "dt_id")
    private Long dtId;

    @Column(name = "dt_name")
    private String dtName;

    @Column(name = "dt_is_active")
    private Boolean dtIsActive = StringConstants.TRUE;

    @CreationTimestamp
    @Column(name = "department_created_date")
    private Date departmentCreatedDate;

    @UpdateTimestamp
    @Column(name = "department_last_updated_date")
    private Date departmentLastUpdatedDate;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "device_type_id", insertable = false, updatable = false)
    @JsonIgnoreProperties("deviceType")
    private List<Device> deviceList;
}
