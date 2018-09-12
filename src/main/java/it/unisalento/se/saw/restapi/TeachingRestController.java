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
import java.util.Set;

@RestController
@RequestMapping("/teaching")
public class TeachingRestController {

    @Autowired
    ITeachingServices teachingServices;


    @RequestMapping(value = "/getAll", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<TeachingDTO> getAll() {
        return teachingServices.getAll();
    }

    @PostMapping(value = "/save", consumes = MediaType.APPLICATION_JSON_VALUE)
    public TeachingDTO save(@RequestBody TeachingDTO teachingDTO) throws CourseNotFoundException, ProfessorNotFoundException {
        return teachingServices.save(teachingDTO);
    }

    @RequestMapping(value = "/getById/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public TeachingDTO getById(@PathVariable int id) throws TeachingNotFoundException {
        try {
            return teachingServices.getById(id);
        } catch (TeachingNotFoundException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    @RequestMapping(value = "/getByIdCourse/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<TeachingDTO> getByIdCourse(@PathVariable int id) throws TeachingNotFoundException {
        return teachingServices.getByIdCourse(id);
    }

    @RequestMapping(value = "/getByIdProf/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<TeachingDTO> getByProf(@PathVariable int id) throws TeachingNotFoundException {
        return teachingServices.getByProf(id);

    }

    @RequestMapping(value = "/getByNameAndIdCourse/{name}_{idCourse}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public TeachingDTO getByNameAndIdCourse(@PathVariable("name") String name, @PathVariable("idCourse") int idCourse) throws TeachingNotFoundException {
        try {
            return teachingServices.getByNameAndIdCourse(name, idCourse);
        } catch (TeachingNotFoundException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    @RequestMapping(value = "/getByNameAndIdProf/{name}_{idProf}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public TeachingDTO getByNameAndIdProf(@PathVariable("name") String name, @PathVariable("idProf") int idProf) throws TeachingNotFoundException {
        try {
            return teachingServices.getByNameAndIdProf(name, idProf);
        } catch (TeachingNotFoundException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
}
