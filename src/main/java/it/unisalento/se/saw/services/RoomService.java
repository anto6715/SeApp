package it.unisalento.se.saw.services;

import it.unisalento.se.saw.Iservices.IRoomServices;
import it.unisalento.se.saw.domain.Room;
import it.unisalento.se.saw.dto.RoomDTO;
import it.unisalento.se.saw.exceptions.RoomNotFoundException;
import it.unisalento.se.saw.models.AbstractFactory;
import it.unisalento.se.saw.models.DTOFactory.DTO;
import it.unisalento.se.saw.models.DomainFactory.Domain;
import it.unisalento.se.saw.models.FactoryProducer;
import it.unisalento.se.saw.repositories.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

@Service
public class RoomService implements IRoomServices {

    @Autowired
    RoomRepository roomRepository;

    AbstractFactory domainFactory = FactoryProducer.getFactory("DOMAIN");
    AbstractFactory dtoFactory = FactoryProducer.getFactory("DTO");

    @Transactional(readOnly = true)
    public Set<RoomDTO> getAll() {
        DTO<List<Room>, Set<RoomDTO>> dto = dtoFactory.getDTO("SETROOM");
        return dto.create(roomRepository.findAll());
    }

    @Transactional(readOnly = true)
    public Set<RoomDTO> getByCapacity(int capacity) {
        DTO<List<Room>, Set<RoomDTO>> dto = dtoFactory.getDTO("SETROOM");
        return dto.create(roomRepository.findRoomsByCapacity(capacity));
    }
    @Transactional
    public RoomDTO getById(int id) throws RoomNotFoundException {
        try {
            DTO<Room, RoomDTO> dto = dtoFactory.getDTO("Room");
            return dto.create(roomRepository.getOne(id));
        } catch (Exception e) {
            throw new RoomNotFoundException();
        }
    }

    @Transactional
    public Room getDomainById(int id) throws RoomNotFoundException {
        try {
            return roomRepository.getOne(id);
        } catch (Exception e) {
            throw new RoomNotFoundException();
        }
    }
    @Transactional
    public RoomDTO save(RoomDTO roomDTO) {
        DTO<Room, RoomDTO> dto = dtoFactory.getDTO("Room");
        Domain<RoomDTO, Room> domain = domainFactory.getDomain("Room");
        return dto.create(roomRepository.save(domain.create(roomDTO)));
    }
}
