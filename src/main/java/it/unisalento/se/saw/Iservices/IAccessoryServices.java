package it.unisalento.se.saw.Iservices;

import it.unisalento.se.saw.domain.Accessory;
import it.unisalento.se.saw.dto.AccessoryDTO;
import it.unisalento.se.saw.exceptions.AccessoryNotFoundException;
import it.unisalento.se.saw.exceptions.RoomNotFoundException;

import java.util.List;

public interface IAccessoryServices {

    public List<Accessory> getAll();
    public Accessory getById(int id) throws AccessoryNotFoundException;
    public List<Accessory> getByIdRoom(int id);
    public Accessory save(AccessoryDTO accessoryDTO) throws RoomNotFoundException;
    public void remove(int id) throws AccessoryNotFoundException;


}
