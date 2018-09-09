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


    @RequestMapping(value = "/getAll", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Set<StudentDTO> getAll(){
        return studentServices.getAll();
    }

    @RequestMapping(value = "getById/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public StudentDTO getById(@PathVariable("id") int id) throws StudentNotFoundException {
        try {
            return studentServices.getById(id);
        } catch (StudentNotFoundException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }


    @RequestMapping(value = "getByUid/{uid}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public StudentDTO getByUid(@PathVariable("uid") String uid) throws StudentNotFoundException {
        try {
            return studentServices.getByUid(uid);
        } catch (StudentNotFoundException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    @RequestMapping(value = "getByCourse/{idCourse}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Set<StudentDTO> getByCourse(@PathVariable("idCourse") int course) {
        return studentServices.getByCourse(course);
    }


    @PostMapping(value = "/save", consumes = MediaType.APPLICATION_JSON_VALUE)
    public StudentDTO post(@RequestBody StudentDTO studentDTO) throws CourseNotFoundException {
        return studentServices.save(studentDTO);
    }
}
