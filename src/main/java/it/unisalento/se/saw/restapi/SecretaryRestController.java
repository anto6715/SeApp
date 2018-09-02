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
import java.util.Set;

@RestController
@RequestMapping("secretary")
public class SecretaryRestController {

    @Autowired
    ISecretaryServices secretaryServices;

    @RequestMapping(value = "/getById/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public SecretaryDTO getById(@PathVariable("id") int id) throws SecretaryNotFoundException {
        return  secretaryServices.getById(id);
    }

    @RequestMapping(value = "/getByUid/{uid}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public SecretaryDTO getByUid(@PathVariable("uid") String uid) throws SecretaryNotFoundException {
        return  secretaryServices.getByUid(uid);
    }

    @PostMapping(value = "/save", consumes = MediaType.APPLICATION_JSON_VALUE)
    public SecretaryDTO post(@RequestBody SecretaryDTO secretaryDTO) throws SecretaryNotFoundException {
        return secretaryServices.save(secretaryDTO);
    }


}
