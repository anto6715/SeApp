package it.unisalento.se.saw.restapi;


import it.unisalento.se.saw.Iservices.ISegnalationServices;
import it.unisalento.se.saw.domain.Segnalation;
import it.unisalento.se.saw.dto.SegnalationDTO;
import it.unisalento.se.saw.exceptions.ProfessorNotFoundException;
import it.unisalento.se.saw.exceptions.RoomNotFoundException;
import it.unisalento.se.saw.exceptions.SegnalationNotFoundException;
import it.unisalento.se.saw.exceptions.SegnalationStateNotFoundException;
import it.unisalento.se.saw.models.AbstractFactory;
import it.unisalento.se.saw.models.DTOFactory.DTO;
import it.unisalento.se.saw.models.FactoryProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("segnalation")
public class SegnalationRestController {

    @Autowired
    ISegnalationServices segnalationServices;


    @RequestMapping(value = "/getAll", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<SegnalationDTO> getAll() {
        return segnalationServices.getAll();
    }

    @PostMapping(value = "/save", consumes = MediaType.APPLICATION_JSON_VALUE)
    public SegnalationDTO save(@RequestBody SegnalationDTO segnalationDTO) throws SegnalationStateNotFoundException, ProfessorNotFoundException, RoomNotFoundException {
        return segnalationServices.save(segnalationDTO);
    }

    @PostMapping(value = "/update", consumes = MediaType.APPLICATION_JSON_VALUE)
    public SegnalationDTO update(@RequestBody SegnalationDTO segnalationDTO) throws SegnalationStateNotFoundException, ProfessorNotFoundException, RoomNotFoundException {
        return segnalationServices.update(segnalationDTO);
    }

    @RequestMapping(value = "/getById/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public SegnalationDTO getById(@PathVariable int id) throws SegnalationNotFoundException {
        try {
            return segnalationServices.getById(id);
        } catch (SegnalationNotFoundException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    @RequestMapping(value = "/getByRoom/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<SegnalationDTO> getByRoom(@PathVariable int id) {
        return segnalationServices.getByRoom(id);
    }

    @RequestMapping(value = "/getByIdProfessor/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<SegnalationDTO> getByProfessor(@PathVariable int id) {
        return segnalationServices.getByProfessor(id);
    }
}
