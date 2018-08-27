package it.unisalento.se.saw.models.DTOFactory;

import it.unisalento.se.saw.domain.Room;
import it.unisalento.se.saw.dto.RoomDTO;

public class RoomDTOModel implements DTO<Room, RoomDTO> {
    @Override
    public RoomDTO create(Room room) {
        RoomDTO roomDTO = new RoomDTO();
        roomDTO.setId(room.getIdRoom());
        roomDTO.setCapacity(room.getCapacity());
        roomDTO.setLatitude(room.getLatitude());
        roomDTO.setLongitude(room.getLongitude());
        roomDTO.setLocation(room.getLocation());
        roomDTO.setName(room.getName());
        return roomDTO;
    }
}
