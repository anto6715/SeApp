package it.unisalento.se.saw.restapi;

import it.unisalento.se.saw.Iservices.IStudentServices;
import it.unisalento.se.saw.domain.Student;
import it.unisalento.se.saw.dto.StudentDTO;
import it.unisalento.se.saw.exceptions.StudentNotFoundException;
import it.unisalento.se.saw.exceptions.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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
        Student student = studentServices.getById(id);
        StudentDTO studentDTO = new StudentDTO();
        studentDTO.setMatricola(student.getMatricola());
        studentDTO.setYear(student.getYear());
        studentDTO.setYearStart(student.getYearStart());
        return studentDTO;
    }

    @RequestMapping(value = "getByCourse/{course}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Student> getByCourse(@PathVariable("course") int course) throws StudentNotFoundException {
        return studentServices.getByCourse(course);
    }

    @RequestMapping(value = "getByName/{name}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public StudentDTO getByName(@PathVariable("name") String name) throws StudentNotFoundException, UserNotFoundException {
        StudentDTO studentDTO = studentServices.getByName(name);
        return studentDTO;
    }
}
