package it.unisalento.se.saw.services;

import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingException;
import com.google.firebase.messaging.Message;
import it.unisalento.se.saw.Iservices.INotificationServices;
import it.unisalento.se.saw.configurations.FirebaseSDKConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@ComponentScan("com.google")
public class NotificationService implements INotificationServices {


    @Transactional
    public void sendNotification(String title, String body, String token) throws FirebaseMessagingException {

        FirebaseSDKConfiguration firebaseSDKConfiguration = new FirebaseSDKConfiguration();
        firebaseSDKConfiguration.initializeSDK();

        Message message = Message.builder()
                .putData("Title:",title)
                .putData("body:",body)
                .setToken(token)
                .build();

        String response = FirebaseMessaging.getInstance().send(message);
        System.out.println("Successfully sent message: " + response);


    }
}
