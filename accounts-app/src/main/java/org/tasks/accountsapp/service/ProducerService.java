package org.tasks.accountsapp.service;

import org.tasks.commons.model.NotificationModel;

public interface ProducerService {
    void notificate(String topic, NotificationModel notificationModel);
}
