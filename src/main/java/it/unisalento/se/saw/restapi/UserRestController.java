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

    AbstractFactory abstractDTOFactory;

    public UserRestController() {
        super();
        this.abstractDTOFactory = FactoryProducer.getFactory("DTO");
    }

    public UserRestController(IUserServices userServices) {
        this.userServices = userServices;
    }

    @RequestMapping(value = "/getAll", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ManyToOne
    public Set<UserDTO> getAll() {
        DTO<List<User>, Set<UserDTO>> dto = this.abstractDTOFactory.getDTO("SetUser");
        return dto.create(userServices.getAll());
    }

    @RequestMapping(value = "/getById/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public UserDTO getById(@PathVariable("id") int id) throws UserNotFoundException{
        try{
            DTO<User, UserDTO> dto = this.abstractDTOFactory.getDTO("User");
            return dto.create(userServices.getById(id));
        } catch (Exception e) {
            throw new UserNotFoundException();
        }

    }


    @RequestMapping(value = "/getByUid/{uid}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Object getByUid(@PathVariable("uid") String uid) throws UserNotFoundException, StudentNotFoundException, ProfessorNotFoundException, SecretaryNotFoundException {
        User user = userServices.getByUid(uid);
        if(user.getUserType() ==1){
           try{
               DTO<Student, StudentDTO> dto = this.abstractDTOFactory.getDTO("Student");
               return dto.create(studentServices.getByUid(uid));
           } catch (Exception e) {
               throw new StudentNotFoundException();
           }
        }
        if (user.getUserType() == 2) {
            try {
                DTO<Secretary, SecretaryDTO> dto = this.abstractDTOFactory.getDTO("Secretary");
                return dto.create(secretaryServices.getByUid(uid));
            } catch (Exception e) {
                throw new SecretaryNotFoundException();
            }
        }
        if (user.getUserType() == 3) {
            try {
                DTO<Professor, ProfessorDTO> dto = this.abstractDTOFactory.getDTO("Professor");
                return dto.create(professorServices.getByUid(uid));
            } catch (Exception e) {
                throw new ProfessorNotFoundException();
            }
        }
        return null;
    }

    @PostMapping(value = "/save", consumes = MediaType.APPLICATION_JSON_VALUE)      // va usata la domain factory
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

    @RequestMapping(value = "/addFcmToken", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public TokenDTO addFcmToken(@RequestBody TokenDTO tokenDTO) {
        return userServices.addFcmToken(tokenDTO);

    }

    @RequestMapping(value = "/deleteFcmToken/{id}", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public void deleteFcmToken(@PathVariable int id) {
        userServices.deleteFcmToken(id);

    }


}
