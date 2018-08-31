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



    AbstractFactory abstractDTOFactory;

    public SecretaryRestController() {
        super();
        this.abstractDTOFactory = FactoryProducer.getFactory("DTO");
    }


        @RequestMapping(value = "/getAll", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ManyToOne
    public Set<SecretaryDTO> getAll() {
        DTO<List<Secretary>, Set<SecretaryDTO>> dto = this.abstractDTOFactory.getDTO("SetSecretary");
        return dto.create(secretaryServices.getAll());
    }

    @RequestMapping(value = "/getById/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public SecretaryDTO getById(@PathVariable("id") int id) throws SecretaryNotFoundException {
        DTO<Secretary, SecretaryDTO> dto = this.abstractDTOFactory.getDTO("SECRETARY");
        return  dto.create(secretaryServices.getById(id));
    }

    @PostMapping(value = "/save", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Secretary post(@RequestBody SecretaryDTO secretaryDTO) throws SecretaryNotFoundException {
        return secretaryServices.save(secretaryDTO);
    }


}
