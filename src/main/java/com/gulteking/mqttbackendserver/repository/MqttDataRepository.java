package com.gulteking.mqttbackendserver.repository;

import com.gulteking.mqttbackendserver.entity.MqttData;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface MqttDataRepository extends PagingAndSortingRepository<MqttData, Long> {
    MqttData findTopByOrderByMqttDataIdDesc();

    Page<MqttData> findAllByMqttDataTopic(String topic, Pageable pageable);
}
