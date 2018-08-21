package it.unisalento.se.saw.restapi;


import it.unisalento.se.saw.Iservices.ICourseServices;
import it.unisalento.se.saw.Iservices.IProfessorServices;
import it.unisalento.se.saw.domain.Course;
import it.unisalento.se.saw.domain.Professor;
import it.unisalento.se.saw.dto.CourseDTO;
import it.unisalento.se.saw.exceptions.CourseNotFoundException;
import it.unisalento.se.saw.exceptions.ProfessorNotFoundException;
import it.unisalento.se.saw.models.AbstractFactory;
import it.unisalento.se.saw.models.DTO;
import it.unisalento.se.saw.models.DtoFactory;
import it.unisalento.se.saw.models.FactoryProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.persistence.ManyToOne;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/course")
public class CourseRestController {

    @Autowired
    ICourseServices courseServices;

    @Autowired
    IProfessorServices professorServices;

    public CourseRestController() {
        super();
    }

    public CourseRestController(ICourseServices courseServices) {
        this.courseServices = courseServices;
    }

    @RequestMapping(value = "/getAll", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ManyToOne
    public List<Course> getAll() {
        return courseServices.getAll();
    }

    @RequestMapping(value = "/getById/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public CourseDTO getById(@PathVariable("id") int id) throws CourseNotFoundException {
        Course course = courseServices.getById(id);
        DtoFactory dtoFactory = new DtoFactory();
        DTO<Course,CourseDTO> dto = dtoFactory.getDTO("COURSE");
        CourseDTO courseDTO = dto.create(course);
        return  courseDTO;
    }

    @RequestMapping(value = "/getByIdProf/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Set<CourseDTO> getByIdProf(@PathVariable("id") int id) throws CourseNotFoundException, ProfessorNotFoundException {
        Professor professor = professorServices.getById(id);
        AbstractFactory abstractFactory = FactoryProducer.getFactory("DTO");
        DTO<Set<Course>, Set<CourseDTO>> dto = abstractFactory.getDTO("SETCOURSE");
        return dto.create(professor.getCourses());
    }

    @RequestMapping(value = "/getByName/{name}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public CourseDTO getByName(@PathVariable("name") String name) throws CourseNotFoundException {
        Course course = courseServices.getByName(name);
        DtoFactory dtoFactory = new DtoFactory();
        DTO<Course,CourseDTO> dto = dtoFactory.getDTO("COURSE");
        CourseDTO courseDTO = dto.create(course);
        return  courseDTO;
    }

    @PostMapping(value = "/save", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Course post(@RequestBody CourseDTO courseDTO) {
        Course course = new Course();
        course.setCredits(courseDTO.getCredits());
        course.setLanguage(courseDTO.getLanguage());
        course.setLenght(courseDTO.getLenght());
        course.setLocation(courseDTO.getLocation());
        course.setName(courseDTO.getName());
        course.setType(courseDTO.getType());
        return courseServices.save(course);
    }

    @RequestMapping(value = "/delete/{id}")
    public void deleteById(@PathVariable("id") int id) throws CourseNotFoundException {
        courseServices.removeCourseById(id);
    }
}
