package it.unisalento.se.saw.restapi;

import it.unisalento.se.saw.Iservices.IAccessoryServices;
import it.unisalento.se.saw.domain.Accessory;
import it.unisalento.se.saw.dto.AccessoryDTO;
import it.unisalento.se.saw.exceptions.AccessoryNotFoundException;
import it.unisalento.se.saw.exceptions.RoomNotFoundException;
import it.unisalento.se.saw.models.AbstractFactory;
import it.unisalento.se.saw.models.FactoryProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/accessory")
public class AccessoryRestController {

    @Autowired
    IAccessoryServices accessoryServices;

    AbstractFactory abstractDTOFactory;

    public AccessoryRestController() {
        super();
        this.abstractDTOFactory = FactoryProducer.getFactory("DTO");
    }

    public  AccessoryRestController(IAccessoryServices accessoryServices) {
        this.accessoryServices = accessoryServices;
    }

    @RequestMapping(value = "/getAll", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Accessory> getAll() {
        return accessoryServices.getAll();
    }

    @PostMapping(value = "/save", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Accessory save(@RequestBody AccessoryDTO accessoryDTO) throws RoomNotFoundException {
        try {
            return accessoryServices.save(accessoryDTO);
        } catch (Exception e) {
            throw new RoomNotFoundException();
        }
    }

    @RequestMapping(value = "/getByIdRoom/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Accessory> getByIdRoom(@PathVariable int id) {
        return accessoryServices.getByIdRoom(id);
    }

    @RequestMapping(value = "/getById/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Accessory getById(@PathVariable int id) throws AccessoryNotFoundException {
        try {
            return accessoryServices.getById(id);
        } catch (Exception e) {
            throw new AccessoryNotFoundException();
        }
    }

    @RequestMapping(value = "/delete/{id}")
    public void deleteById(@PathVariable("id") int id) throws AccessoryNotFoundException {
        try {
            accessoryServices.remove(id);
        } catch (Exception e) {
            throw new AccessoryNotFoundException();
        }
    }
}
