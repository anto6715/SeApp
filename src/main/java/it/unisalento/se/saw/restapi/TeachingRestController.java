package it.unisalento.se.saw.restapi;

import it.unisalento.se.saw.Iservices.ITeachingServices;
import it.unisalento.se.saw.domain.Teaching;
import it.unisalento.se.saw.dto.TeachingDTO;
import it.unisalento.se.saw.exceptions.CourseNotFoundException;
import it.unisalento.se.saw.exceptions.ProfessorNotFoundException;
import it.unisalento.se.saw.exceptions.TeachingNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/teaching")
public class TeachingRestController {

    @Autowired
    ITeachingServices teachingServices;

    public TeachingRestController() {
        super();
    }

    public TeachingRestController(ITeachingServices teachingServices) {
        this.teachingServices = teachingServices;
    }

    @RequestMapping(value = "/getAll", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Teaching> getAll() {
        return teachingServices.getAll();
    }

    @PostMapping(value = "/save", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Teaching save(@RequestBody TeachingDTO teachingDTO) throws CourseNotFoundException, ProfessorNotFoundException {
        return teachingServices.save(teachingDTO);
    }

    @RequestMapping(value = "/getById/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Teaching getById(@PathVariable int id) throws TeachingNotFoundException {
        try {
            return teachingServices.getById(id);
        } catch (Exception e) {
            throw new TeachingNotFoundException();
        }
    }

    @RequestMapping(value = "/getByIdCourse/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Teaching> getByIdCourse(@PathVariable int id) throws TeachingNotFoundException {
        try {
            return teachingServices.getByIdCourse(id);
        } catch (Exception e) {
            throw new TeachingNotFoundException();
        }
    }

    @RequestMapping(value = "/getByName/{name}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Teaching getByName(@PathVariable String name) throws TeachingNotFoundException {
        try {
            return teachingServices.getByName(name);
        } catch (Exception e) {
            throw new TeachingNotFoundException();
        }
    }

    @RequestMapping(value = "/delete/{id}")
    public void deleteById(@PathVariable("id") int id) throws TeachingNotFoundException {
        try {
            teachingServices.remove(id);
        } catch (Exception e) {
            throw new TeachingNotFoundException();
        }
    }
}
