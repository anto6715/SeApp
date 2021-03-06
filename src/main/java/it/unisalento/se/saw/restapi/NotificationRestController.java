package it.unisalento.se.saw.restapi;


import com.google.firebase.messaging.FirebaseMessagingException;
import it.unisalento.se.saw.Iservices.INotificationServices;
import it.unisalento.se.saw.Iservices.IUserServices;
import it.unisalento.se.saw.domain.User;
import it.unisalento.se.saw.dto.NotificationDTO;
import it.unisalento.se.saw.dto.UserDTO;
import it.unisalento.se.saw.exceptions.UserNotFoundException;
import it.unisalento.se.saw.models.AbstractFactory;
import it.unisalento.se.saw.models.DomainFactory.Domain;
import it.unisalento.se.saw.models.FactoryProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/notification")
public class NotificationRestController {

    @Autowired
    INotificationServices notificationServices;

    @Autowired
    IUserServices userServices;

    AbstractFactory abstractFactory = FactoryProducer.getFactory("DOMAIN");

    public NotificationRestController(){
        super();
    }

    public NotificationRestController(INotificationServices notificationServices) {
        this.notificationServices = notificationServices;
    }

    @PostMapping(value = "/sendToUser", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void sendToUser(@RequestBody NotificationDTO notificationDTO) throws UserNotFoundException, FirebaseMessagingException, IOException {
        Domain<UserDTO, User> domain = abstractFactory.getDomain("User");
        User user = domain.create(userServices.getById(notificationDTO.getIdUser()));
        if (user.getToken() == null){
            return;
        }else{
            notificationDTO.setToken_topic(user.getToken());
            this.notificationServices.sendToUser(notificationDTO);
        }
    }

    @PostMapping(value = "/sendToTopic", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void sendToTopic(@RequestBody NotificationDTO notificationDTO) throws  FirebaseMessagingException, IOException {
        this.notificationServices.sendToTopic(notificationDTO);
    }
}
