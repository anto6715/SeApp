package it.unisalento.se.saw.restapi;


import it.unisalento.se.saw.Iservices.ISegnalationServices;
import it.unisalento.se.saw.domain.Segnalation;
import it.unisalento.se.saw.dto.SegnalationDTO;
import it.unisalento.se.saw.exceptions.ProfessorNotFoundException;
import it.unisalento.se.saw.exceptions.RoomNotFoundException;
import it.unisalento.se.saw.exceptions.SegnalationNotFoundException;
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

    AbstractFactory abstractDTOFactory;

    public SegnalationRestController() {
        super();
        this.abstractDTOFactory = FactoryProducer.getFactory("DTO");
    }

    public SegnalationRestController(ISegnalationServices segnalationServices) {
        this.segnalationServices = segnalationServices;
    }

    @RequestMapping(value = "/getAll", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Set<SegnalationDTO> getAll() {
        DTO<List<Segnalation>, Set<SegnalationDTO>> dto = this.abstractDTOFactory.getDTO("SetSegnalation");
        return dto.create(segnalationServices.getAll());
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
    public SegnalationDTO getById(@PathVariable int id) throws SegnalationNotFoundException {
        try {
            DTO<Segnalation, SegnalationDTO> dto = this.abstractDTOFactory.getDTO("Segnalation");
            return dto.create(segnalationServices.getById(id));
        } catch (Exception e) {
            throw new SegnalationNotFoundException();
        }
    }

    @RequestMapping(value = "/getByRoom/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Set<SegnalationDTO> getByRoom(@PathVariable int id) {
        DTO<List<Segnalation>, Set<SegnalationDTO>> dto = this.abstractDTOFactory.getDTO("SetSegnalation");
        return dto.create(segnalationServices.getByRoom(id));
    }

    @RequestMapping(value = "/getByIdProfessor/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Set<SegnalationDTO> getByProfessor(@PathVariable int id) {
        DTO<List<Segnalation>, Set<SegnalationDTO>> dto = this.abstractDTOFactory.getDTO("SetSegnalation");
        return dto.create(segnalationServices.getByProfessor(id));
    }
}
