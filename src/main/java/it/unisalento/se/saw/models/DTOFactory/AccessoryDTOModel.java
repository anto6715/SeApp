package it.unisalento.se.saw.models.DTOFactory;

import it.unisalento.se.saw.domain.Accessory;
import it.unisalento.se.saw.dto.AccessoryDTO;

public class AccessoryDTOModel implements DTO<Accessory, AccessoryDTO> {
    @Override
    public AccessoryDTO create(Accessory accessory) {
        AccessoryDTO accessoryDTO = new AccessoryDTO();
        accessoryDTO.setId(accessory.getId().getIdAccessory());
        accessoryDTO.setIdRoom(accessory.getId().getRoomIdRoom());
        accessoryDTO.setType(accessory.getType());
        return accessoryDTO;
    }
}
