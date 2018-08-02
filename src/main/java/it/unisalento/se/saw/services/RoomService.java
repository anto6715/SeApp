package it.unisalento.se.saw.services;

import it.unisalento.se.saw.Iservices.IRoomServices;
import it.unisalento.se.saw.domain.Room;
import it.unisalento.se.saw.dto.RoomDTO;
import it.unisalento.se.saw.exceptions.RoomNotFoundException;
import it.unisalento.se.saw.repositories.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
public class RoomService implements IRoomServices {

    @Autowired
    RoomRepository roomRepository;

    @Transactional(readOnly = true)
    public List<Room> getAll() {
        return roomRepository.findAll();
    }

    @Transactional(readOnly = true)
    public List<Room> getByCapacity(int capacity) {
        return roomRepository.findRoomsByCapacity(capacity);
    }
    @Transactional
    public Room getById(int id) throws RoomNotFoundException {
        try {
            return roomRepository.getOne(id);
        } catch (Exception e) {
            throw new RoomNotFoundException();
        }
    }
    @Transactional
    public Room getByName(String name) throws RoomNotFoundException {
        try {
            return roomRepository.findRoomByName(name);
        } catch (Exception e) {
            throw new RoomNotFoundException();
        }
    }
    @Transactional
    public Room save(RoomDTO roomDTO) {
        Room room = new Room();
        room.setCapacity(roomDTO.getCapacity());
        room.setLocation(roomDTO.getLocation());
        room.setName(roomDTO.getName());
        System.out.println(roomDTO.getName());
        return roomRepository.save(room);
    }

    public void remove(int id) throws RoomNotFoundException {
        try {
            Room room = roomRepository.getOne(id);
            roomRepository.delete(room);
        } catch (Exception e) {
            throw new RoomNotFoundException();
        }

    }
}
