package it.unisalento.se.saw.models.DomainFactory;

import it.unisalento.se.saw.domain.Room;
import it.unisalento.se.saw.dto.RoomDTO;

public class RoomDomainFactory implements Domain<RoomDTO, Room> {
    @Override
    public Room create(RoomDTO roomDTO) {
        Room room = new Room();
        room.setCapacity(roomDTO.getCapacity());
        room.setLocation(roomDTO.getLocation());
        room.setName(roomDTO.getName());
        room.setLatitude(roomDTO.getLatitude());
        room.setLongitude(roomDTO.getLongitude());
        return room;
    }
}
