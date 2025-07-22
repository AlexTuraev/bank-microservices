package org.tasks.accountsapp.service.impl;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.tasks.accountsapp.commons.model.NotificationModel;
import org.tasks.accountsapp.service.ProducerService;

@Service
public class ProducerServiceImpl implements ProducerService {
    private final KafkaTemplate<String, NotificationModel> kafkaTemplate;

    public ProducerServiceImpl(KafkaTemplate<String, NotificationModel> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @Override
    public void notificate(String topic, NotificationModel notificationModel) {
        kafkaTemplate.send(topic, notificationModel);
    }
}
