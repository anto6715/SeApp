package it.unisalento.se.saw.services;

import it.unisalento.se.saw.Iservices.IAccessoryServices;
import it.unisalento.se.saw.Iservices.IRoomServices;
import it.unisalento.se.saw.domain.Accessory;
import it.unisalento.se.saw.domain.AccessoryId;
import it.unisalento.se.saw.domain.Room;
import it.unisalento.se.saw.dto.AccessoryDTO;
import it.unisalento.se.saw.exceptions.AccessoryNotFoundException;
import it.unisalento.se.saw.exceptions.RoomNotFoundException;
import it.unisalento.se.saw.models.AbstractFactory;
import it.unisalento.se.saw.models.DTOFactory.DTO;
import it.unisalento.se.saw.models.FactoryProducer;
import it.unisalento.se.saw.repositories.AccessoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

@Service
public class AccessoryService implements IAccessoryServices {

    @Autowired
    AccessoryRepository accessoryRepository;

    @Autowired
    IRoomServices roomServices;

    AbstractFactory dtoFactory = FactoryProducer.getFactory("DTO");

    @Transactional(readOnly = true)
    public List<AccessoryDTO> getAll() {
        DTO<List<Accessory>, List<AccessoryDTO>> dto = dtoFactory.getDTO("SETAccessory");
        return dto.create(accessoryRepository.findAll());
    }

    public AccessoryDTO getById(int id) throws AccessoryNotFoundException {
        try {
            DTO<Accessory, AccessoryDTO> dto = dtoFactory.getDTO("Accessory");
            return dto.create(accessoryRepository.findAccessoryById_IdAccessory(id));
        } catch (Exception e) {
            throw new AccessoryNotFoundException();
        }
    }

    public List<AccessoryDTO> getByIdRoom(int id) {
        DTO<List<Accessory>, List<AccessoryDTO>> dto = dtoFactory.getDTO("SETAccessory");
        return dto.create(accessoryRepository.findAccessoriesById_RoomIdRoom(id));
    }

    public AccessoryDTO save(AccessoryDTO accessoryDTO) {
        DTO<Accessory, AccessoryDTO> dto = dtoFactory.getDTO("Accessory");
        Room room = null;
        try {
            room = roomServices.getDomainById(accessoryDTO.getIdRoom());
        } catch (RoomNotFoundException e) {
            e.printStackTrace();
        }
        System.out.println(room.getName());
        AccessoryId accessoryId = new AccessoryId();

        accessoryId.setRoomIdRoom(room.getIdRoom());

        Accessory accessory = new Accessory();
        accessory.setRoom(room);
        accessory.setType(accessoryDTO.getType());
        accessory.setId(accessoryId);
        return dto.create(accessoryRepository.save(accessory));
    }
}
