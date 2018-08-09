package it.unisalento.se.saw.restapi;

import it.unisalento.se.saw.Iservices.IRoomServices;
import it.unisalento.se.saw.domain.Room;
import it.unisalento.se.saw.dto.RoomDTO;
import it.unisalento.se.saw.exceptions.RoomNotFoundException;
import it.unisalento.se.saw.exceptions.UserNotFoundException;
import it.unisalento.se.saw.models.AbstractFactory;
import it.unisalento.se.saw.models.DTO;
import it.unisalento.se.saw.models.FactoryProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/room")
public class RoomRestController {

    @Autowired
    IRoomServices roomServices;

    @RequestMapping(value = "/getAll", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Room> getAll() {
        return roomServices.getAll();
    }

    @PostMapping(value = "/save", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Room save(@RequestBody RoomDTO roomDTO) {
        return roomServices.save(roomDTO);
    }

    @RequestMapping(value = "/getById/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public RoomDTO getById(@PathVariable int id) throws RoomNotFoundException {
        try {

            Room room = roomServices.getById(id);
            AbstractFactory abstractFactory = FactoryProducer.getFactory("DTO");
            DTO<Room,RoomDTO> dto = abstractFactory.getDTO("Room");
            return dto.create(room);
        } catch (Exception e) {
            throw new RoomNotFoundException();
        }
    }

    @RequestMapping(value = "/getByName/{name}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Room getByName(@PathVariable String name) throws RoomNotFoundException {
        try {
            return roomServices.getByName(name);
        } catch (Exception e) {
            throw new RoomNotFoundException();
        }
    }

    @RequestMapping(value = "/getByCapacity/{capacity}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Room> getByCapacity(@PathVariable int capacity) throws RoomNotFoundException {
        try {
            return roomServices.getByCapacity(capacity);
        } catch (Exception e) {
            throw new RoomNotFoundException();
        }
    }

    @RequestMapping(value = "/delete/{id}")
    public void deleteById(@PathVariable("id") int id) throws RoomNotFoundException {
        try {
            roomServices.remove(id);
        } catch (Exception e) {
            throw new RoomNotFoundException();
        }
    }
}
