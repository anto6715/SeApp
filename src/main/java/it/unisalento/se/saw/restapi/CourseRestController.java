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
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/course")
public class CourseRestController {

    @Autowired
    ICourseServices courseServices;

    @Autowired
    IProfessorServices professorServices;


    AbstractFactory dtoFactory = FactoryProducer.getFactory("DTO");
    DTO<List<Course>, List<CourseDTO>> listCourseDto = dtoFactory.getDTO("LISTCOURSE");

    public CourseRestController(ICourseServices courseServices) {
        this.courseServices = courseServices;
    }

    @RequestMapping(value = "/getAll", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ManyToOne
    public List<CourseDTO> getAll() {
        return courseServices.getAll();
    }

    @RequestMapping(value = "/getById/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public CourseDTO getById(@PathVariable("id") int id) throws CourseNotFoundException {
        return  courseServices.getById(id);
    }

    @RequestMapping(value = "/getByIdProf/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<CourseDTO> getByIdProf(@PathVariable("id") int id) throws CourseNotFoundException, ProfessorNotFoundException {
        List<Course> courses = new ArrayList<>(professorServices.getDomainById(id).getCourses());
        return listCourseDto.create(courses);
    }

    @PostMapping(value = "/save", consumes = MediaType.APPLICATION_JSON_VALUE)
    public CourseDTO post(@RequestBody CourseDTO courseDTO) {
        return courseServices.save(courseDTO);
    }
}
