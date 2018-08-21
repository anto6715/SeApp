package it.unisalento.se.saw.restapi;

import it.unisalento.se.saw.Iservices.IProfessorServices;
import it.unisalento.se.saw.Iservices.IStudentServices;
import it.unisalento.se.saw.Iservices.IUserServices;
import it.unisalento.se.saw.domain.Professor;
import it.unisalento.se.saw.domain.Student;
import it.unisalento.se.saw.domain.User;
import it.unisalento.se.saw.dto.ProfessorDTO;
import it.unisalento.se.saw.dto.StudentDTO;
import it.unisalento.se.saw.dto.TokenDTO;
import it.unisalento.se.saw.dto.UserDTO;
import it.unisalento.se.saw.exceptions.ProfessorNotFoundException;
import it.unisalento.se.saw.exceptions.StudentNotFoundException;
import it.unisalento.se.saw.exceptions.UserNotFoundException;
import it.unisalento.se.saw.models.AbstractFactory;
import it.unisalento.se.saw.models.DTO;
import it.unisalento.se.saw.models.FactoryProducer;
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

    @Autowired
    IStudentServices studentServices;

    @Autowired
    IProfessorServices professorServices;

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
        userDTO.setUid(user.getUid());
        userDTO.setUserType((user.getUserType()));
        return userDTO;
    }


    @RequestMapping(value = "/getByUid/{uid}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Object getByUid(@PathVariable("uid") String uid) throws UserNotFoundException, StudentNotFoundException, ProfessorNotFoundException {
        User user = userServices.getByUid(uid);
        AbstractFactory abstractFactory = FactoryProducer.getFactory("DTO");
        if(user.getUserType() ==1){
           try{
               DTO<Student, StudentDTO> dto = abstractFactory.getDTO("Student");
               return dto.create(studentServices.getByUid(uid));
           } catch (Exception e) {
               throw new StudentNotFoundException();
           }
        }

        if (user.getUserType() == 3) {
            try {
                DTO<Professor, ProfessorDTO> dto = abstractFactory.getDTO("Professor");
                return dto.create(professorServices.getByUid(uid));
            } catch (Exception e) {
                throw new ProfessorNotFoundException();
            }
        }


        return null;
    }

    @RequestMapping(value = "/getByName/{name}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<User> getByName(@PathVariable("name") String name){
               return userServices.getByName(name);
    }

    @RequestMapping(value = "/getByNameSurname/{name}_{surname}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public UserDTO getByNameSurname(@PathVariable("name") String name, @PathVariable("surname") String surname) throws UserNotFoundException{
        User user= userServices.getByNameSurname(name, surname);
        UserDTO userDTO = new UserDTO();
        userDTO.setName(user.getName());
        userDTO.setSurname(user.getSurname());
        userDTO.setEmail(user.getEmail());
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


    @RequestMapping(value = "/addFcmToken", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public TokenDTO addFcmToken(@RequestBody TokenDTO tokenDTO) {
        return userServices.addFcmToken(tokenDTO);

    }

    @RequestMapping(value = "/deleteFcmToken/{id}", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public void deleteFcmToken(@PathVariable int id) {
        userServices.deleteFcmToken(id);

    }


}
