package it.unisalento.se.saw.restapi;

import it.unisalento.se.saw.Iservices.IStudentServices;
import it.unisalento.se.saw.domain.Student;
import it.unisalento.se.saw.dto.CourseDTO;
import it.unisalento.se.saw.dto.StudentDTO;
import it.unisalento.se.saw.exceptions.CourseNotFoundException;
import it.unisalento.se.saw.exceptions.StudentNotFoundException;
import it.unisalento.se.saw.exceptions.UserNotFoundException;
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
            StudentDTO studentDTO = new StudentDTO();
            studentDTO.setYearStart(student.getYearStart());
            studentDTO.setYear(student.getYear());
            studentDTO.setMatricola(student.getMatricola());
            studentDTO.setAge(student.getUser().getAge());
            studentDTO.setEmail(student.getUser().getEmail());
            studentDTO.setName(student.getUser().getName());
            studentDTO.setSurname(student.getUser().getSurname());
            studentDTO.setUid(student.getUser().getUid());
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
            StudentDTO studentDTO = new StudentDTO();
            studentDTO.setYearStart(student.getYearStart());
            studentDTO.setYear(student.getYear());
            studentDTO.setMatricola(student.getMatricola());
            studentDTO.setAge(student.getUser().getAge());
            studentDTO.setEmail(student.getUser().getEmail());
            studentDTO.setName(student.getUser().getName());
            studentDTO.setSurname(student.getUser().getSurname());
            studentDTO.setUid(student.getUser().getUid());
            studentDTO.setUserType(student.getUser().getUserType());
            studentDTO.setIdCourse(student.getCourse().getIdCourse());
            CourseDTO courseDTO = new CourseDTO();
            courseDTO.setName(student.getCourse().getName());
            courseDTO.setLanguage(student.getCourse().getLanguage());
            courseDTO.setLocation(student.getCourse().getLocation());
            courseDTO.setCredits(student.getCourse().getCredits());
            courseDTO.setType(student.getCourse().getType());
            courseDTO.setLenght(student.getCourse().getLenght());
            studentDTO.setCourseDTO(courseDTO);
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
    public StudentDTO getByName(@PathVariable("name") String name) throws StudentNotFoundException, UserNotFoundException {
        Student student = studentServices.getByName(name);
        StudentDTO studentDTO = new StudentDTO();
        studentDTO.setYearStart(student.getYearStart());
        studentDTO.setYear(student.getYear());
        studentDTO.setMatricola(student.getMatricola());
        studentDTO.setAge(student.getUser().getAge());
        studentDTO.setEmail(student.getUser().getEmail());
        studentDTO.setName(student.getUser().getName());
        studentDTO.setSurname(student.getUser().getSurname());
        studentDTO.setUid(student.getUser().getUid());
        return studentDTO;
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
