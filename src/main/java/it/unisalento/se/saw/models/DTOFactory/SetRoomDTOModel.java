package it.unisalento.se.saw.models.DTOFactory;

import it.unisalento.se.saw.domain.Room;
import it.unisalento.se.saw.dto.RoomDTO;
import it.unisalento.se.saw.models.AbstractFactory;
import it.unisalento.se.saw.models.FactoryProducer;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class SetRoomDTOModel implements DTO<List<Room>, List<RoomDTO>>{
    @Override
    public List<RoomDTO> create(List<Room> rooms) {
        AbstractFactory abstractFactory = FactoryProducer.getFactory("DTO");
        DTO<Room, RoomDTO> dto = abstractFactory.getDTO("Room");
        List<RoomDTO> roomDTOS = new ArrayList<>();
        for (Room room: rooms) {
            roomDTOS.add(dto.create(room));
        }
        return roomDTOS;
    }
}
