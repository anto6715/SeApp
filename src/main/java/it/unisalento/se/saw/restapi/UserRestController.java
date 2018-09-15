package it.unisalento.se.saw.restapi;

import it.unisalento.se.saw.Iservices.IProfessorServices;
import it.unisalento.se.saw.Iservices.ISecretaryServices;
import it.unisalento.se.saw.Iservices.IStudentServices;
import it.unisalento.se.saw.Iservices.IUserServices;
import it.unisalento.se.saw.domain.Professor;
import it.unisalento.se.saw.domain.Secretary;
import it.unisalento.se.saw.domain.Student;
import it.unisalento.se.saw.domain.User;
import it.unisalento.se.saw.dto.*;
import it.unisalento.se.saw.exceptions.ProfessorNotFoundException;
import it.unisalento.se.saw.exceptions.SecretaryNotFoundException;
import it.unisalento.se.saw.exceptions.StudentNotFoundException;
import it.unisalento.se.saw.exceptions.UserNotFoundException;
import it.unisalento.se.saw.models.AbstractFactory;
import it.unisalento.se.saw.models.DTOFactory.DTO;
import it.unisalento.se.saw.models.FactoryProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.persistence.ManyToOne;
import java.util.List;
import java.util.Set;

@RestController // contiene 2 annotation: Controller e responseBody
@RequestMapping("/user")
public class UserRestController {

    @Autowired
    IUserServices userServices;

    @Autowired
    IStudentServices studentServices;

    @Autowired
    IProfessorServices professorServices;

    @Autowired
    ISecretaryServices secretaryServices;

    AbstractFactory abstractDTOFactory = FactoryProducer.getFactory("DTO");


    @RequestMapping(value = "/getAll", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ManyToOne
    public List<UserDTO> getAll() {
        DTO<List<User>, List<UserDTO>> dto = this.abstractDTOFactory.getDTO("SetUser");
        return userServices.getAll();
    }



    @RequestMapping(value = "/getById/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public UserDTO getById(@PathVariable("id") int id) {
        try {
            return userServices.getById(id);
        } catch (UserNotFoundException e) {
            System.out.println(e.getMessage());
            return null;
        }

    }


    @RequestMapping(value = "/getByUid/{uid}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Object getByUid(@PathVariable("uid") String uid)  {
        try {
            return userServices.getByUid(uid);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }

    }

    @PostMapping(value = "/save", consumes = MediaType.APPLICATION_JSON_VALUE)
    public UserDTO save(@RequestBody UserDTO userDTO) {
        return userServices.save(userDTO);
    }

    @RequestMapping(value = "/addFcmToken", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public TokenDTO addFcmToken(@RequestBody TokenDTO tokenDTO) {
        return userServices.addFcmToken(tokenDTO);

    }


}
