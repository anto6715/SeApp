package it.unisalento.se.saw.restapi;

import it.unisalento.se.saw.Iservices.ISecretaryServices;
import it.unisalento.se.saw.domain.Secretary;
import it.unisalento.se.saw.dto.SecretaryDTO;
import it.unisalento.se.saw.exceptions.SecretaryNotFoundException;
import it.unisalento.se.saw.models.AbstractFactory;
import it.unisalento.se.saw.models.DTOFactory.DTO;
import it.unisalento.se.saw.models.FactoryProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.persistence.ManyToOne;
import java.util.List;

@RestController
@RequestMapping("secretary")
public class SecretaryRestController {

    @Autowired
    ISecretaryServices secretaryServices;

    @RequestMapping(value = "/getAll", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ManyToOne
    public List<Secretary> getAll() {
        return secretaryServices.getAll();
    }

    @RequestMapping(value = "/getById/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public SecretaryDTO getById(@PathVariable("id") int id) throws SecretaryNotFoundException {
        Secretary secretary = secretaryServices.getById(id);
        AbstractFactory dtoFactory = FactoryProducer.getFactory("DTO");
        DTO<Secretary, SecretaryDTO> dto = dtoFactory.getDTO("SECRETARY");
        SecretaryDTO secretaryDTO = dto.create(secretary);
        return secretaryDTO;
    }

    @PostMapping(value = "/save", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Secretary post(@RequestBody SecretaryDTO secretaryDTO) throws SecretaryNotFoundException {
        return secretaryServices.save(secretaryDTO);
    }

    @RequestMapping(value = "/delete/{id}")
    public void deleteById(@PathVariable("id") int id) throws SecretaryNotFoundException {
        secretaryServices.removeById(id);
    }


}
