package it.unisalento.se.saw.services;

import it.unisalento.se.saw.Iservices.IUserServices;
import it.unisalento.se.saw.domain.User;
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

}
