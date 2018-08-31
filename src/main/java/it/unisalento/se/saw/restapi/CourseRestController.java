package it.unisalento.se.saw.restapi;


import it.unisalento.se.saw.Iservices.ICourseServices;
import it.unisalento.se.saw.Iservices.IProfessorServices;
import it.unisalento.se.saw.domain.Course;
import it.unisalento.se.saw.domain.Professor;
import it.unisalento.se.saw.dto.CourseDTO;
import it.unisalento.se.saw.exceptions.CourseNotFoundException;
import it.unisalento.se.saw.exceptions.ProfessorNotFoundException;
import it.unisalento.se.saw.models.AbstractFactory;
import it.unisalento.se.saw.models.DTOFactory.DTO;
import it.unisalento.se.saw.models.DTOFactory.DtoFactory;
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

    AbstractFactory abstractDTOFactory;

    public CourseRestController() {
        super();
        this.abstractDTOFactory = FactoryProducer.getFactory("DTO");
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
        DTO<Course,CourseDTO> dto = abstractDTOFactory.getDTO("COURSE");
        return  dto.create(courseServices.getById(id));
    }

    @RequestMapping(value = "/getByIdProf/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Set<CourseDTO> getByIdProf(@PathVariable("id") int id) throws CourseNotFoundException, ProfessorNotFoundException {
        DTO<Set<Course>, Set<CourseDTO>> dto = this.abstractDTOFactory.getDTO("SETCOURSE");
        return dto.create(professorServices.getById(id).getCourses());
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
}
