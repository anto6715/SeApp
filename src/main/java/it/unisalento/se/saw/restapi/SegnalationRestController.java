package it.unisalento.se.saw.restapi;


import it.unisalento.se.saw.Iservices.ISegnalationServices;
import it.unisalento.se.saw.domain.Segnalation;
import it.unisalento.se.saw.dto.SegnalationDTO;
import it.unisalento.se.saw.exceptions.ProfessorNotFoundException;
import it.unisalento.se.saw.exceptions.RoomNotFoundException;
import it.unisalento.se.saw.exceptions.SegnalationNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("segnalation")
public class SegnalationRestController {

    @Autowired
    ISegnalationServices segnalationServices;

    public SegnalationRestController() {
        super();
    }

    public SegnalationRestController(ISegnalationServices segnalationServices) {
        this.segnalationServices = segnalationServices;
    }

    @RequestMapping(value = "/getAll", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Segnalation> getAll() {
        return segnalationServices.getAll();
    }

    @PostMapping(value = "/save", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Segnalation save(@RequestBody SegnalationDTO segnalationDTO) throws RoomNotFoundException, ProfessorNotFoundException {
        try {
            return segnalationServices.save(segnalationDTO);
        } catch (Exception e) {
            throw new RoomNotFoundException();
        }
    }

    @RequestMapping(value = "/getById/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Segnalation getById(@PathVariable int id) throws SegnalationNotFoundException {
        try {
            return segnalationServices.getById(id);
        } catch (Exception e) {
            throw new SegnalationNotFoundException();
        }
    }

    @RequestMapping(value = "/getByRoom/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Segnalation> getByRoom(@PathVariable int id) {
        return segnalationServices.getByRoom(id);
    }

    @RequestMapping(value = "/getByProfessor/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Segnalation> getByProfessor(@PathVariable int id) {
        return segnalationServices.getByProfessor(id);
    }
}
