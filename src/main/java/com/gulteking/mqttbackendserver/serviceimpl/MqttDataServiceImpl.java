package com.gulteking.mqttbackendserver.serviceimpl;

import com.gulteking.mqttbackendserver.entity.MqttData;
import com.gulteking.mqttbackendserver.repository.MqttDataRepository;
import com.gulteking.mqttbackendserver.service.MqttDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MqttDataServiceImpl implements MqttDataService {
    @Autowired
    MqttDataRepository mqttDataRepository;


    @Override
    public List<MqttData> findAll() {
        return (List<MqttData>) mqttDataRepository.findAll();
    }

    @Override
    public Optional<MqttData> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public MqttData save(MqttData mqttData) {
        return mqttDataRepository.save(mqttData);
    }

    @Override
    public MqttData update(MqttData mqttData) {
        return mqttDataRepository.save(mqttData);
    }

    @Override
    public List<MqttData> findLastRecord() {
        return mqttDataRepository.findAll(PageRequest.of(0, 50, Sort.by("mqttDataId").descending())).getContent();
    }

    @Override
    public List<MqttData> findLastRecordsByTopic(String topicName) {
        Pageable page = PageRequest.of(0, 20, Sort.by("mqttDataId").descending());
        return mqttDataRepository.findAllByMqttDataTopic(topicName, page).getContent();
    }
}
