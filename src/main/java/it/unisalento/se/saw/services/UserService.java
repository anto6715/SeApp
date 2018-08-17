package it.unisalento.se.saw.services;

import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingException;
import com.google.firebase.messaging.Message;
import it.unisalento.se.saw.Iservices.IUserServices;
import it.unisalento.se.saw.domain.User;
import it.unisalento.se.saw.dto.TokenDTO;
import it.unisalento.se.saw.exceptions.UserNotFoundException;
import it.unisalento.se.saw.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserService implements IUserServices {

    @Autowired
    UserRepository userRepository;

    @Transactional(readOnly=true)
    public List<User> getAll(){
        return userRepository.findAll();
    }

    @Transactional
    public User save(User user) {
        return userRepository.save(user);
    }

    @Transactional
    public List<User> getByName(String name){
        return userRepository.findUsersByName(name);
    }

    @Transactional
    public User getById(int id) throws UserNotFoundException {
        try {
            User user = userRepository.getOne(id);
            return user;
        } catch (Exception e) {
            throw new UserNotFoundException();
        }

    }


    @Transactional(rollbackFor = UserNotFoundException.class)
    public void removeUserById(int id) throws UserNotFoundException {
        try {
            User user = userRepository.getOne(id);
            userRepository.delete(user);
        } catch (Exception e){
            throw new UserNotFoundException();
        }
    }

    @Transactional
    public User updateName(int id, String name) throws UserNotFoundException {
        try {
            User user = userRepository.getOne(id);
            user.setName(name);
            userRepository.save(user);
            return user;
        }catch (Exception e) {
            throw new UserNotFoundException();
        }
    }
    @Transactional
    public User getByNameSurname(String name, String surname) throws UserNotFoundException {
        try {
            return userRepository.getUserByNameAndSurname(name, surname);
        } catch (Exception e) {
            throw new UserNotFoundException();
        }
    }

    @Transactional
    public TokenDTO addFcmToken(TokenDTO tokenDTO){
        User user = userRepository.getOne(tokenDTO.getIdUser());
        user.setToken(tokenDTO.getToken());
        User tkn = userRepository.save(user);
        TokenDTO token = new TokenDTO();
        token.setIdUser(tkn.getIdUser());
        token.setToken(tkn.getToken());
        return token;
    }

    @Transactional
    public void deleteFcmToken(int id){
        User user = userRepository.getOne(id);
        user.setToken(null);
        User tkn = userRepository.save(user);
    }

    @Transactional
    public void send() throws FirebaseMessagingException {
        // This registration token comes from the client FCM SDKs.
        String registrationToken = "YOUR_REGISTRATION_TOKEN";

// See documentation on defining a message payload.
        Message message = Message.builder()
                .putData("score", "850")
                .putData("time", "2:45")
                .setToken(registrationToken)
                .build();

// Send a message to the device corresponding to the provided
// registration token.
        String response = FirebaseMessaging.getInstance().send(message);
// Response is a message ID string.
        System.out.println("Successfully sent message: " + response);
    }

}
