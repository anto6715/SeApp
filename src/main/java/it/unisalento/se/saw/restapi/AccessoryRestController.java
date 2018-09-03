package it.unisalento.se.saw.restapi;

import it.unisalento.se.saw.Iservices.IAccessoryServices;
import it.unisalento.se.saw.dto.AccessoryDTO;
import it.unisalento.se.saw.exceptions.AccessoryNotFoundException;
import it.unisalento.se.saw.exceptions.RoomNotFoundException;
import it.unisalento.se.saw.models.AbstractFactory;
import it.unisalento.se.saw.models.FactoryProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/accessory")
public class AccessoryRestController {

    @Autowired
    IAccessoryServices accessoryServices;


    @RequestMapping(value = "/getAll", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Set<AccessoryDTO> getAll() {
        return accessoryServices.getAll();
    }

    @PostMapping(value = "/save", consumes = MediaType.APPLICATION_JSON_VALUE)
    public AccessoryDTO save(@RequestBody AccessoryDTO accessoryDTO) throws RoomNotFoundException {
        return accessoryServices.save(accessoryDTO);
    }

    @RequestMapping(value = "/getByIdRoom/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Set<AccessoryDTO> getByIdRoom(@PathVariable int id) {
        return accessoryServices.getByIdRoom(id);
    }

    @RequestMapping(value = "/getById/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public AccessoryDTO getById(@PathVariable int id) throws AccessoryNotFoundException {
        return accessoryServices.getById(id);
    }
}
