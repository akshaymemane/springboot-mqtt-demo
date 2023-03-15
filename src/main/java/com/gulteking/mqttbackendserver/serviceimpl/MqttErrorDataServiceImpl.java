package com.gulteking.mqttbackendserver.serviceimpl;

import com.gulteking.mqttbackendserver.entity.MqttErrorData;
import com.gulteking.mqttbackendserver.repository.MqttErrorDataRepository;
import com.gulteking.mqttbackendserver.service.MqttErrorDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MqttErrorDataServiceImpl implements MqttErrorDataService {

    @Autowired
    private MqttErrorDataRepository mqttErrorDataRepository;


    @Override
    public List<MqttErrorData> findAll() {
        return mqttErrorDataRepository.findAll();
    }

    @Override
    public Optional<MqttErrorData> findById(Long id) {
        return mqttErrorDataRepository.findById(id);
    }

    @Override
    public MqttErrorData save(MqttErrorData mqttErrorData) {
        return mqttErrorDataRepository.save(mqttErrorData);
    }

    @Override
    public MqttErrorData update(MqttErrorData mqttErrorData) {
        return mqttErrorDataRepository.save(mqttErrorData);
    }

    @Override
    public List<MqttErrorData> findLastRecord() {
        return mqttErrorDataRepository.findAll(PageRequest.of(0, 50, Sort.by("mqttErrorDataId").descending())).getContent();
    }

    @Override
    public List<MqttErrorData> findLastRecordsByTopic(String topicName) {
        return mqttErrorDataRepository.findAllByMqttErrorDataTopic(topicName);
    }
}
