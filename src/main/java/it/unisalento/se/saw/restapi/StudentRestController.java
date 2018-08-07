package it.unisalento.se.saw.restapi;

import it.unisalento.se.saw.Iservices.IStudentServices;
import it.unisalento.se.saw.domain.Student;
import it.unisalento.se.saw.domain.User;
import it.unisalento.se.saw.dto.CourseDTO;
import it.unisalento.se.saw.dto.StudentDTO;
import it.unisalento.se.saw.exceptions.CourseNotFoundException;
import it.unisalento.se.saw.exceptions.StudentNotFoundException;
import it.unisalento.se.saw.exceptions.UserNotFoundException;
import it.unisalento.se.saw.models.DTO;
import it.unisalento.se.saw.models.DtoFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.persistence.ManyToOne;
import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentRestController {

    @Autowired
    IStudentServices studentServices;

    public StudentRestController() {
        super();
    }

    public StudentRestController(IStudentServices studentServices) {
        this.studentServices = studentServices;
    }

    @RequestMapping(value = "/getAll", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ManyToOne
    public List<Student> getAll(){
        return studentServices.getAll();
    }

    @RequestMapping(value = "getById/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public StudentDTO getById(@PathVariable("id") int id) throws StudentNotFoundException {
        try{
            Student student = studentServices.getById(id);
            DtoFactory dtoFactory = new DtoFactory();
            DTO<Student,StudentDTO> dto = dtoFactory.getDTO("STUDENT");
            StudentDTO studentDTO = dto.create(student);
            return studentDTO;
        } catch (Exception e) {
            System.out.println("Utente non trovato");
        }
        return null;
    }


    @RequestMapping(value = "getByUid/{uid}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public StudentDTO getByUid(@PathVariable("uid") String uid) throws StudentNotFoundException {
        try{
            Student student = studentServices.getByUid(uid);
            DtoFactory dtoFactory = new DtoFactory();
            DTO<Student,StudentDTO> dto = dtoFactory.getDTO("STUDENT");
            StudentDTO studentDTO = dto.create(student);
            return studentDTO;
        } catch (Exception e) {
            System.out.println("Utente non trovato");
        }
        return null;
    }

    @RequestMapping(value = "getByCourse/{course}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Student> getByCourse(@PathVariable("course") int course) throws StudentNotFoundException {
        return studentServices.getByCourse(course);
    }

    @RequestMapping(value = "getByName/{name}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Student> getByName(@PathVariable("name") String name) throws StudentNotFoundException, UserNotFoundException {
        return studentServices.getByName(name);
    }


    @PostMapping(value = "/save", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Student post(@RequestBody StudentDTO studentDTO) throws CourseNotFoundException {
        return studentServices.save(studentDTO);
    }

    @RequestMapping(value = "/delete/{id}")
    public void deleteById(@PathVariable("id") int id) throws StudentNotFoundException {
        studentServices.removeById(id);
    }
}
