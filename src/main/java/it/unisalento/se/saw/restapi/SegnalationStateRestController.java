package it.unisalento.se.saw.restapi;


import it.unisalento.se.saw.Iservices.ISegnalationStateServices;
import it.unisalento.se.saw.domain.SegnalationState;
import it.unisalento.se.saw.dto.SegnalationStateDTO;
import it.unisalento.se.saw.exceptions.SegnalationStateNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("segnalationState")
public class SegnalationStateRestController {


    @Autowired
    ISegnalationStateServices segnalationStateServices;

    @RequestMapping(value = "/getAll", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<SegnalationStateDTO> getAll() {
        return segnalationStateServices.getAll();
    }

    @PostMapping(value = "/save", consumes = MediaType.APPLICATION_JSON_VALUE)
    public SegnalationStateDTO save(@RequestBody SegnalationStateDTO segnalationStateDTO) {
        return segnalationStateServices.save(segnalationStateDTO);
    }

    @RequestMapping(value = "/getById/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public SegnalationStateDTO getById(@PathVariable int id) throws SegnalationStateNotFoundException {
        try {
            return segnalationStateServices.getById(id);
        } catch (SegnalationStateNotFoundException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
}
