package it.unisalento.se.saw.Iservices;

import com.google.firebase.messaging.FirebaseMessagingException;

public interface INotificationServices {

    public void sendNotification(String title, String body, String token) throws FirebaseMessagingException;
}
