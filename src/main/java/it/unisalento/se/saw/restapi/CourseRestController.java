package it.unisalento.se.saw.restapi;


import it.unisalento.se.saw.Iservices.ICourseServices;
import it.unisalento.se.saw.domain.Course;
import it.unisalento.se.saw.dto.CourseDTO;
import it.unisalento.se.saw.exceptions.CourseNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.persistence.ManyToOne;
import java.util.List;

@RestController
@RequestMapping("/course")
public class CourseRestController {

    @Autowired
    ICourseServices courseServices;

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
        CourseDTO courseDTO = new CourseDTO();
        courseDTO.setCredits(course.getCredits());
        courseDTO.setLanguage(course.getLanguage());
        courseDTO.setLenght(course.getLenght());
        courseDTO.setLocation(course.getLocation());
        courseDTO.setName(course.getName());
        courseDTO.setType(course.getType());
        return  courseDTO;
    }

    @RequestMapping(value = "/getByName/{name}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public CourseDTO getByName(@PathVariable("name") String name) throws CourseNotFoundException {
        System.out.println(name);
        Course course = courseServices.getByName(name);
        CourseDTO courseDTO = new CourseDTO();
        courseDTO.setCredits(course.getCredits());
        courseDTO.setLanguage(course.getLanguage());
        courseDTO.setLenght(course.getLenght());
        courseDTO.setLocation(course.getLocation());
        courseDTO.setName(course.getName());
        courseDTO.setType(course.getType());
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
        return courseServices.save(course);
    }

    @RequestMapping(value = "/delete/{id}")
    public void deleteById(@PathVariable("id") int id) throws CourseNotFoundException {
        courseServices.removeCourseById(id);
    }
}