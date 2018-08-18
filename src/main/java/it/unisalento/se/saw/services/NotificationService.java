package it.unisalento.se.saw.services;


import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingException;
import com.google.firebase.messaging.Message;
import it.unisalento.se.saw.Iservices.INotificationServices;
import it.unisalento.se.saw.configurations.FirebaseSDKConfiguration;
import it.unisalento.se.saw.dto.NotificationDTO;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;

@Service
public class NotificationService implements INotificationServices {



    @Transactional
    public void sendToUser(NotificationDTO notificationDTO) throws FirebaseMessagingException, IOException {

        FirebaseSDKConfiguration.initialize();


        Message message = Message.builder()
                .putData("title", notificationDTO.getTitle())
                .putData("body", notificationDTO.getBody())
                .putData("data", notificationDTO.getData())
                .setToken(notificationDTO.getToken_topic())
                .build();

        String response = FirebaseMessaging.getInstance().send(message);
        System.out.println(response);
    }

    public void sendToTopic(NotificationDTO notificationDTO) throws FirebaseMessagingException, IOException {

        FirebaseSDKConfiguration.initialize();

        Message message = Message.builder()
                .putData("title", notificationDTO.getTitle())
                .putData("body", notificationDTO.getBody())
                .putData("data", notificationDTO.getData())
                .setTopic(notificationDTO.getToken_topic())
                .build();

        String response = FirebaseMessaging.getInstance().send(message);
    }
}
