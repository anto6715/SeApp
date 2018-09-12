package it.unisalento.se.saw.restapi;


import it.unisalento.se.saw.Iservices.IProfessorServices;
import it.unisalento.se.saw.domain.Professor;
import it.unisalento.se.saw.dto.ProfessorDTO;
import it.unisalento.se.saw.exceptions.CourseNotFoundException;
import it.unisalento.se.saw.exceptions.ProfessorNotFoundException;
import it.unisalento.se.saw.models.AbstractFactory;
import it.unisalento.se.saw.models.DTOFactory.DTO;
import it.unisalento.se.saw.models.FactoryProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("professor")
public class ProfessorRestController {

    @Autowired
    IProfessorServices professorServices;

    @RequestMapping(value = "/getAll", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<ProfessorDTO> getAll() {
        return professorServices.getAll();
    }

    @PostMapping(value = "/save", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ProfessorDTO save(@RequestBody ProfessorDTO professorDTO) throws CourseNotFoundException {
        return professorServices.save(professorDTO);
    }

    @RequestMapping(value = "/getById/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ProfessorDTO getById(@PathVariable("id") int id) throws ProfessorNotFoundException {
       return professorServices.getById(id);
    }

    @RequestMapping(value = "/getByCourse/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<ProfessorDTO> getByIdCourse(@PathVariable("id") int id) throws ProfessorNotFoundException {
        return professorServices.getByIdCourse(id);
    }


}
