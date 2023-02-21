package com.gulteking.mqttbackendserver.repository;

import com.gulteking.mqttbackendserver.entity.Device;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DeviceRepository extends JpaRepository<Device, Long> {

    List<Device> findAllByDeviceIsActiveTrue();

    List<Device> findAllByDeviceIsActiveFalse();

    Optional<Device> findByDevicePublisherUrl(String devicePublisherUrl);

    Optional<Device> findByDeviceSerialId(String deviceSerialId);

}

