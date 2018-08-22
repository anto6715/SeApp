package it.unisalento.se.saw.restapi;

import it.unisalento.se.saw.Iservices.ITeachingServices;
import it.unisalento.se.saw.domain.Teaching;
import it.unisalento.se.saw.dto.TeachingDTO;
import it.unisalento.se.saw.exceptions.CourseNotFoundException;
import it.unisalento.se.saw.exceptions.ProfessorNotFoundException;
import it.unisalento.se.saw.exceptions.TeachingNotFoundException;
import it.unisalento.se.saw.models.AbstractFactory;
import it.unisalento.se.saw.models.DTO;
import it.unisalento.se.saw.models.FactoryProducer;
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

    public TeachingRestController() {
        super();
        System.out.println("avvio");
    }

    public TeachingRestController(ITeachingServices teachingServices) {
        this.teachingServices = teachingServices;
    }

    @RequestMapping(value = "/getAll", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Set<TeachingDTO> getAll() {
        AbstractFactory abstractFactory = FactoryProducer.getFactory("DTO");
        DTO<List<Teaching>, Set<TeachingDTO>> dto = abstractFactory.getDTO("SETTEACHING");
        List<Teaching> teachings = teachingServices.getAll();
        return dto.create(teachings);
    }

    @PostMapping(value = "/save", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Teaching save(@RequestBody TeachingDTO teachingDTO) throws CourseNotFoundException, ProfessorNotFoundException {
        return teachingServices.save(teachingDTO);
    }

    @RequestMapping(value = "/getById/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public TeachingDTO getById(@PathVariable int id) throws TeachingNotFoundException {
        try {
            Teaching teaching= teachingServices.getById(id);
            AbstractFactory abstractFactory = FactoryProducer.getFactory("DTO");
            DTO<Teaching, TeachingDTO> dto = abstractFactory.getDTO("Teaching");
            return dto.create(teaching);
        } catch (Exception e) {
            throw new TeachingNotFoundException();
        }
    }

    @RequestMapping(value = "/getByIdCourse/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Set<TeachingDTO> getByIdCourse(@PathVariable int id) throws TeachingNotFoundException {
        AbstractFactory abstractFactory = FactoryProducer.getFactory("DTO");
        DTO<List<Teaching>, Set<TeachingDTO>> dto = abstractFactory.getDTO("SETTEACHING");
        List<Teaching> teachings = teachingServices.getByIdCourse(id);
        return dto.create(teachings);

    }

    @RequestMapping(value = "/getByIdProf/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Set<TeachingDTO> getByProf(@PathVariable int id) throws TeachingNotFoundException {
        AbstractFactory abstractFactory = FactoryProducer.getFactory("DTO");
        DTO<List<Teaching>, Set<TeachingDTO>> dto = abstractFactory.getDTO("SETTEACHING");
        List<Teaching> teachings = teachingServices.getByProf(id);
        return dto.create(teachings);

    }

    @RequestMapping(value = "/getByNameAndIdCourse/{name}_{idCourse}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public TeachingDTO getByNameAndIdCourse(@PathVariable("name") String name, @PathVariable("idCourse") int idCourse) throws TeachingNotFoundException {
        try {
            AbstractFactory abstractFactory = FactoryProducer.getFactory("DTO");
            DTO<Teaching, TeachingDTO> dto = abstractFactory.getDTO("Teaching");

            return dto.create(teachingServices.getByNameAndIdCourse(name, idCourse));
        } catch (Exception e) {
            throw new TeachingNotFoundException();
        }
    }

    @RequestMapping(value = "/getByNameAndIdProf/{name}_{idProf}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public TeachingDTO getByNameAndIdProf(@PathVariable("name") String name, @PathVariable("idProf") int idProf) throws TeachingNotFoundException {
        try {
            AbstractFactory abstractFactory = FactoryProducer.getFactory("DTO");
            DTO<Teaching, TeachingDTO> dto = abstractFactory.getDTO("Teaching");

            return dto.create(teachingServices.getByNameAndIdProf(name, idProf));
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
