package it.unisalento.se.saw.restapi;

import com.google.firebase.messaging.FirebaseMessagingException;
import it.unisalento.se.saw.Iservices.INotificationServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
@RequestMapping("/notification")
public class NotificationRestController {



    public NotificationRestController(){
        super();
    }



   /* @RequestMapping(value = "/sendNotification", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public void sendNotification() throws FirebaseMessagingException {
        notificationServices.sendNotification("Nuovo Messaggio", "Image Processing", "ciao");
    }*/
}
