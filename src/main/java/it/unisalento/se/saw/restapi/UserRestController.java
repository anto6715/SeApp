package it.unisalento.se.saw.restapi;

import it.unisalento.se.saw.Iservices.IUserServices;
import it.unisalento.se.saw.domain.User;
import it.unisalento.se.saw.dto.UserDTO;
import it.unisalento.se.saw.exceptions.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.persistence.ManyToOne;
import java.util.List;

@RestController // contiene 2 annotation: Controller e responseBody
@RequestMapping("/user")
public class UserRestController {

    @Autowired
    IUserServices userServices;

    public UserRestController() {
        super();
    }

    public UserRestController(IUserServices userServices) {
        this.userServices = userServices;
    }


    @RequestMapping(value = "/getAll", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ManyToOne
    public List<User> getAll() {
        return userServices.getAll();
    }

    @RequestMapping(value = "/getById/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public UserDTO getById(@PathVariable("id") int id) throws UserNotFoundException{
        User user= userServices.getById(id);
        UserDTO userDTO = new UserDTO();
        userDTO.setName(user.getName());
        userDTO.setSurname(user.getSurname());
        userDTO.setEmail(user.getEmail());
        userDTO.setPassword(user.getPassword());
        userDTO.setUid(user.getUid());
        userDTO.setUserType((user.getUserType()));
        return userDTO;
    }

    @RequestMapping(value = "/getByName/{name}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public UserDTO getByName(@PathVariable("name") String name) throws UserNotFoundException{
        User user= userServices.getByName(name);
        UserDTO userDTO = new UserDTO();
        userDTO.setName(user.getName());
        userDTO.setSurname(user.getSurname());
        userDTO.setEmail(user.getEmail());
        userDTO.setPassword(user.getPassword());
        userDTO.setUid(user.getUid());
        userDTO.setUserType((user.getUserType()));
        return userDTO;
    }

    @RequestMapping(value = "/getByNameSurname/{name}_{surname}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public UserDTO getByNameSurname(@PathVariable("name") String name, @PathVariable("surname") String surname) throws UserNotFoundException{
        User user= userServices.getByNameSurname(name, surname);
        UserDTO userDTO = new UserDTO();
        userDTO.setName(user.getName());
        userDTO.setSurname(user.getSurname());
        userDTO.setEmail(user.getEmail());
        userDTO.setPassword(user.getPassword());
        userDTO.setUid(user.getUid());
        userDTO.setUserType((user.getUserType()));
        return userDTO;
    }

    @PostMapping(value = "/save", consumes = MediaType.APPLICATION_JSON_VALUE)
    public User post(@RequestBody UserDTO userDTO) {
        User user = new User();
        user.setName(userDTO.getName());
        user.setSurname(userDTO.getSurname());
        user.setAge(userDTO.getAge());
        user.setEmail(userDTO.getEmail());
        user.setPassword(userDTO.getPassword());
        user.setUid(userDTO.getUid());
        user.setUserType(userDTO.getUserType());
        return userServices.save(user);
    }

    @RequestMapping(value = "/update/{id}_{name}", produces = MediaType.APPLICATION_JSON_VALUE)
    public User updateNameById(@PathVariable("id") int id, @PathVariable("name") String name) throws UserNotFoundException{
        User user = userServices.updateName(id, name);
        return user;
    }

    @RequestMapping(value = "/delete/{id}")
    public void deleteById(@PathVariable("id") int id) throws UserNotFoundException {
        userServices.removeUserById(id);
    }
}
