package org.tasks.notificationsapp.service;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.tasks.commons.model.NotificationModel;

public interface NotificationsListenerService {
    void listenNotifications(ConsumerRecord<String, NotificationModel> record);
}
