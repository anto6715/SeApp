package it.unisalento.se.saw.restapi;

import it.unisalento.se.saw.Iservices.IStudentServices;
import it.unisalento.se.saw.domain.Student;
import it.unisalento.se.saw.dto.StudentDTO;
import it.unisalento.se.saw.exceptions.CourseNotFoundException;
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

@RestController
@RequestMapping("/student")
public class StudentRestController {

    @Autowired
    IStudentServices studentServices;

    AbstractFactory abstractDTOFactory;

    public StudentRestController() {
        super();
        this.abstractDTOFactory = FactoryProducer.getFactory("DTO");
    }

    public StudentRestController(IStudentServices studentServices) {
        this.studentServices = studentServices;
    }

    @RequestMapping(value = "/getAll", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ManyToOne
    public Set<StudentDTO> getAll(){
        DTO<List<Student>, Set<StudentDTO>> dto = this.abstractDTOFactory.getDTO("SETSTUDENT");
        return dto.create(studentServices.getAll());
    }

    @RequestMapping(value = "getById/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public StudentDTO getById(@PathVariable("id") int id) throws StudentNotFoundException {
        try{
            DTO<Student,StudentDTO> dto = this.abstractDTOFactory.getDTO("STUDENT");
            return dto.create(studentServices.getById(id));
        } catch (Exception e) {
            System.out.println("Utente non trovato");
        }
        return null;
    }


    @RequestMapping(value = "getByUid/{uid}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public StudentDTO getByUid(@PathVariable("uid") String uid) throws StudentNotFoundException {
        try{
            DTO<Student,StudentDTO> dto = this.abstractDTOFactory.getDTO("STUDENT");
            return dto.create(studentServices.getByUid(uid));
        } catch (Exception e) {
            System.out.println("Utente non trovato");
        }
        return null;
    }

    @RequestMapping(value = "getByCourse/{course}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Set<StudentDTO> getByCourse(@PathVariable("course") int course) {
        DTO<List<Student>, Set<StudentDTO>> dto = this.abstractDTOFactory.getDTO("SETSTUDENT");
        return dto.create(studentServices.getByCourse(course));
    }


    @PostMapping(value = "/save", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Student post(@RequestBody StudentDTO studentDTO) throws CourseNotFoundException {
        System.out.println(studentDTO.getIdCourse());
        return studentServices.save(studentDTO);
    }
}
