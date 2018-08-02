package it.unisalento.se.saw.Iservices;

import it.unisalento.se.saw.domain.Room;
import it.unisalento.se.saw.dto.RoomDTO;
import it.unisalento.se.saw.exceptions.RoomNotFoundException;

import java.util.List;

public interface IRoomServices {

    public List<Room> getAll();
    public Room getById(int id) throws RoomNotFoundException;
    public Room getByName(String name) throws RoomNotFoundException;
    public List<Room> getByCapacity(int capacity);
    public Room save(RoomDTO roomDTO);
    public void remove(int id) throws RoomNotFoundException;
}
