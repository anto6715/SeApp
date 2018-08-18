package it.unisalento.se.saw.Iservices;

import com.google.firebase.messaging.FirebaseMessagingException;
import it.unisalento.se.saw.dto.NotificationDTO;

import java.io.IOException;

public interface INotificationServices {

    public void sendToUser(NotificationDTO notificationDTO) throws FirebaseMessagingException, IOException;
    public void sendToTopic(NotificationDTO notificationDTO) throws FirebaseMessagingException, IOException;
}
