package it.unisalento.se.saw.services;

import it.unisalento.se.saw.Iservices.IAccessoryServices;
import it.unisalento.se.saw.Iservices.IRoomServices;
import it.unisalento.se.saw.domain.Accessory;
import it.unisalento.se.saw.domain.AccessoryId;
import it.unisalento.se.saw.domain.Room;
import it.unisalento.se.saw.dto.AccessoryDTO;
import it.unisalento.se.saw.exceptions.AccessoryNotFoundException;
import it.unisalento.se.saw.exceptions.RoomNotFoundException;
import it.unisalento.se.saw.repositories.AccessoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AccessoryService implements IAccessoryServices {

    @Autowired
    AccessoryRepository accessoryRepository;

    @Autowired
    IRoomServices roomServices;

    @Transactional(readOnly = true)
    public List<Accessory> getAll() {
        return accessoryRepository.findAll();
    }

    public Accessory getById(int id) throws AccessoryNotFoundException {
        try {
            return accessoryRepository.findAccessoryById_IdAccessory(id);
        } catch (Exception e) {
            throw new AccessoryNotFoundException();
        }
    }

    public List<Accessory> getByIdRoom(int id) {
        return accessoryRepository.findAccessoriesById_RoomIdRoom(id);
    }

    public Accessory save(AccessoryDTO accessoryDTO) throws RoomNotFoundException {
        try {
            Room room = roomServices.getDomainById(accessoryDTO.getIdRoom());
            System.out.println(room.getName());
            AccessoryId accessoryId = new AccessoryId();

            accessoryId.setRoomIdRoom(room.getIdRoom());

            Accessory accessory = new Accessory();
            accessory.setRoom(room);
            accessory.setType(accessoryDTO.getType());
            accessory.setId(accessoryId);
            return accessoryRepository.save(accessory);
        } catch (Exception e) {
            throw new RoomNotFoundException();
        }
    }

    public void remove(int id) throws AccessoryNotFoundException {
        try {
            Accessory accessory = accessoryRepository.findAccessoryById_IdAccessory(id);
            accessoryRepository.delete(accessory);
        } catch (Exception e) {
            throw new AccessoryNotFoundException();
        }

    }
}
