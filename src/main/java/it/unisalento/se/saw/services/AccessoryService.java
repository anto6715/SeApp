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
    DTO<List<Accessory>, List<AccessoryDTO>> listAccessoryDto = dtoFactory.getDTO("LISTACCESSORY");
    DTO<Accessory, AccessoryDTO> accessoryDto = dtoFactory.getDTO("Accessory");

    @Transactional(readOnly = true)
    public List<AccessoryDTO> getAll() {
        return listAccessoryDto.create(accessoryRepository.findAll());
    }

    public AccessoryDTO getById(int id) throws AccessoryNotFoundException {
        try {
            return accessoryDto.create(accessoryRepository.findAccessoryById_IdAccessory(id));
        } catch (Exception e) {
            throw new AccessoryNotFoundException();
        }
    }

    public List<AccessoryDTO> getByIdRoom(int id) {
        return listAccessoryDto.create(accessoryRepository.findAccessoriesById_RoomIdRoom(id));
    }

    public AccessoryDTO save(AccessoryDTO accessoryDTO) {
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
        return accessoryDto.create(accessoryRepository.save(accessory));
    }
}
