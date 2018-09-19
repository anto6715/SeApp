package it.unisalento.se.saw.services;

import it.unisalento.se.saw.Iservices.IRoomServices;
import it.unisalento.se.saw.domain.Lesson;
import it.unisalento.se.saw.domain.Room;
import it.unisalento.se.saw.dto.RoomDTO;
import it.unisalento.se.saw.exceptions.RoomNotFoundException;
import it.unisalento.se.saw.models.AbstractFactory;
import it.unisalento.se.saw.models.DTOFactory.DTO;
import it.unisalento.se.saw.models.DomainFactory.Domain;
import it.unisalento.se.saw.models.FactoryProducer;
import it.unisalento.se.saw.repositories.LessonRepository;
import it.unisalento.se.saw.repositories.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Set;

@Service
public class RoomService implements IRoomServices {

    @Autowired
    RoomRepository roomRepository;

    @Autowired
    LessonRepository lessonRepository;

    AbstractFactory domainFactory = FactoryProducer.getFactory("DOMAIN");
    AbstractFactory dtoFactory = FactoryProducer.getFactory("DTO");
    DTO<List<Room>, List<RoomDTO>> listRoomDto = dtoFactory.getDTO("LISTROOM");
    DTO<Room, RoomDTO> roomDto = dtoFactory.getDTO("Room");


    @Transactional(readOnly = true)
    public List<RoomDTO> getAll() {
        return listRoomDto.create(roomRepository.findAll());
    }

    @Transactional(readOnly = true)
    public List<RoomDTO> getByCapacity(int capacity) {
        return listRoomDto.create(roomRepository.findRoomsByCapacity(capacity));
    }
    @Transactional
    public RoomDTO getById(int id) throws RoomNotFoundException {
        try {
            return roomDto.create(roomRepository.getOne(id));
        } catch (Exception e) {
            throw new RoomNotFoundException();
        }
    }

    @Transactional
    public Room getDomainById(int id) throws RoomNotFoundException {
        return roomRepository.getOne(id);
    }

    @Transactional
    public RoomDTO save(RoomDTO roomDTO) {
        Domain<RoomDTO, Room> domain = domainFactory.getDomain("Room");
        return roomDto.create(roomRepository.save(domain.create(roomDTO)));
    }

    @Transactional
    public boolean checkDisponibility(Date date, int id, Date start, Date end) {
        List<Lesson> lessonsStart = lessonRepository.findLessonsByDateAndId_RoomIdRoomAndStartBetween(date, id, start,end);
        List<Lesson> lessonsEnd = lessonRepository.findLessonsByDateAndId_RoomIdRoomAndEndBetween(date, id, start,end);

        if (lessonsStart.size() == 0 && lessonsEnd.size()== 0) return true;
        else  return false;
    }
}
