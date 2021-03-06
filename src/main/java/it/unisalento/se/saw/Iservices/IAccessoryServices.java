package it.unisalento.se.saw.Iservices;

import it.unisalento.se.saw.dto.AccessoryDTO;
import it.unisalento.se.saw.exceptions.AccessoryNotFoundException;
import it.unisalento.se.saw.exceptions.RoomNotFoundException;

import java.util.List;
import java.util.Set;

public interface IAccessoryServices {

    public List<AccessoryDTO> getAll();
    public AccessoryDTO getById(int id) throws AccessoryNotFoundException;
    public List<AccessoryDTO> getByIdRoom(int id);
    public AccessoryDTO save(AccessoryDTO accessoryDTO) throws RoomNotFoundException;


}
