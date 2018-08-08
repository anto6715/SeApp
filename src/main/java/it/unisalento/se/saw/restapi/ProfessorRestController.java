package it.unisalento.se.saw.restapi;


import it.unisalento.se.saw.Iservices.IProfessorServices;
import it.unisalento.se.saw.domain.Professor;
import it.unisalento.se.saw.dto.ProfessorDTO;
import it.unisalento.se.saw.exceptions.CourseNotFoundException;
import it.unisalento.se.saw.exceptions.ProfessorNotFoundException;
import it.unisalento.se.saw.models.AbstractFactory;
import it.unisalento.se.saw.models.DTO;
import it.unisalento.se.saw.models.DtoFactory;
import it.unisalento.se.saw.models.FactoryProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("professor")
public class ProfessorRestController {

    @Autowired
    IProfessorServices professorServices;

    @RequestMapping(value = "/getAll", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Professor> getAll() {
        return professorServices.getAll();
    }

    @PostMapping(value = "/save", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Professor post(@RequestBody ProfessorDTO professorDTO) throws CourseNotFoundException {
        System.out.println("qui");
        return professorServices.save(professorDTO);
    }

    @RequestMapping(value = "/getById/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ProfessorDTO getById(@PathVariable("id") int id) throws ProfessorNotFoundException {
       try {
           Professor professor = professorServices.getById(id);
           AbstractFactory dtoFactory = FactoryProducer.getFactory("DTO");
           DTO<Professor,ProfessorDTO> dto = dtoFactory.getDTO("PROFESSOR");
           ProfessorDTO professorDTO = dto.create(professor);
           return professorDTO;
       } catch (Exception e) {
           throw new ProfessorNotFoundException();
       }

    }

    @RequestMapping(value = "/getByName/{name}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Professor> getByName(@PathVariable("name") String name) throws ProfessorNotFoundException {
        try {
            return professorServices.getByName(name);
        } catch (Exception e) {
            throw new ProfessorNotFoundException();
        }

    }

    @RequestMapping(value = "/getBySurname/{surname}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Professor> getBySurname(@PathVariable("surname") String surname) throws ProfessorNotFoundException {
        try {
            return professorServices.getBySurname(surname);
        } catch (Exception e) {
            throw new ProfessorNotFoundException();
        }

    }

    @RequestMapping(value = "/getByCourse/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Set<Professor> getByCourse(@PathVariable("id") int id) throws ProfessorNotFoundException {
        try {
            return professorServices.getByIdCourse(id);
        } catch (Exception e) {
            throw new ProfessorNotFoundException();
        }

    }


}
