package org.tasks.notificationsapp.service.impl;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import org.tasks.notificationsapp.commons.model.NotificationModel;
import org.tasks.notificationsapp.service.NotificationsListenerService;

@Service
public class NotificationsListenerServiceImpl implements NotificationsListenerService {

    @Override
    @KafkaListener(topics = "notifications")
    public void listenNotifications(ConsumerRecord<String, NotificationModel> record) {
        System.out.println("Received record: " + record.value());
    }

}
