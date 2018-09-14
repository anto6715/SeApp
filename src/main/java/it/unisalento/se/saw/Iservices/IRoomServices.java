package it.unisalento.se.saw.Iservices;

import it.unisalento.se.saw.domain.Room;
import it.unisalento.se.saw.dto.RoomDTO;
import it.unisalento.se.saw.exceptions.RoomNotFoundException;

import java.util.Date;
import java.util.List;
import java.util.Set;

public interface IRoomServices {

    public List<RoomDTO> getAll();
    public RoomDTO getById(int id) throws RoomNotFoundException;
    public Room getDomainById(int id) throws RoomNotFoundException;
    public List<RoomDTO> getByCapacity(int capacity);
    public RoomDTO save(RoomDTO roomDTO);
    public boolean checkDisponibility(Date date, int id, Date end);
}
