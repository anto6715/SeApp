package it.unisalento.se.saw.restapi;

import it.unisalento.se.saw.Iservices.IRoomServices;
import it.unisalento.se.saw.domain.Room;
import it.unisalento.se.saw.dto.RoomDTO;
import it.unisalento.se.saw.exceptions.RoomNotFoundException;
import it.unisalento.se.saw.models.AbstractFactory;
import it.unisalento.se.saw.models.DTOFactory.DTO;
import it.unisalento.se.saw.models.FactoryProducer;
import it.unisalento.se.saw.models.Strategy.Context;
import it.unisalento.se.saw.models.Strategy.DateParseStrategy;
import it.unisalento.se.saw.models.Strategy.TimeParseStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/room")
public class RoomRestController {

    @Autowired
    IRoomServices roomServices;


    @RequestMapping(value = "/getAll", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<RoomDTO> getAll() {
        return roomServices.getAll();
    }

    @PostMapping(value = "/save", consumes = MediaType.APPLICATION_JSON_VALUE)
    public RoomDTO save(@RequestBody RoomDTO roomDTO) {
        return roomServices.save(roomDTO);
    }

    @RequestMapping(value = "/getById/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public RoomDTO getById(@PathVariable int id) throws RoomNotFoundException {
        return roomServices.getById(id);
    }

    @RequestMapping(value = "/checkDisponibility/{date}_{id}_{start}_{end}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE, headers = "Accept=application/json")
    public boolean checkDisponibility(@PathVariable("date") String date, @PathVariable("id") int id, @PathVariable("start") String start,@PathVariable("end") String end) throws ParseException {
        Context context = new Context((new TimeParseStrategy()));
        Date endObj= context.executeDateStrategy(end);
        Date startObj = context.executeDateStrategy(start);
        startObj.setMinutes(startObj.getMinutes()+1);
        endObj.setMinutes(endObj.getMinutes()-1);
        System.out.println(endObj);

        context.changeStrategy(new DateParseStrategy());
        Date dateObj = context.executeDateStrategy(date);
        return roomServices.checkDisponibility(dateObj,id,startObj,endObj);
    }

    @RequestMapping(value = "/getByCapacity/{capacity}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<RoomDTO> getByCapacity(@PathVariable int capacity) {
        return roomServices.getByCapacity(capacity);

    }
}
