package it.unisalento.se.saw.restapi;

import it.unisalento.se.saw.Iservices.IRoomServices;
import it.unisalento.se.saw.domain.Room;
import it.unisalento.se.saw.dto.RoomDTO;
import it.unisalento.se.saw.exceptions.RoomNotFoundException;
import it.unisalento.se.saw.models.AbstractFactory;
import it.unisalento.se.saw.models.DTOFactory.DTO;
import it.unisalento.se.saw.models.FactoryProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/room")
public class RoomRestController {

    @Autowired
    IRoomServices roomServices;

    AbstractFactory abstractDTOFactory;

    public RoomRestController() {
        super();
        this.abstractDTOFactory = FactoryProducer.getFactory("DTO");
    }

    @RequestMapping(value = "/getAll", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Set<RoomDTO> getAll() {
        return roomServices.getAll();
    }

    @PostMapping(value = "/save", consumes = MediaType.APPLICATION_JSON_VALUE)
    public RoomDTO save(@RequestBody RoomDTO roomDTO) {
        return roomServices.save(roomDTO);
    }

    @RequestMapping(value = "/getById/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public RoomDTO getById(@PathVariable int id) throws RoomNotFoundException {
        try {
            return roomServices.getById(id);
        } catch (Exception e) {
            throw new RoomNotFoundException();
        }
    }

    @RequestMapping(value = "/getByCapacity/{capacity}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Set<RoomDTO> getByCapacity(@PathVariable int capacity) throws RoomNotFoundException {
        try {
            return roomServices.getByCapacity(capacity);
        } catch (Exception e) {
            throw new RoomNotFoundException();
        }
    }
}
