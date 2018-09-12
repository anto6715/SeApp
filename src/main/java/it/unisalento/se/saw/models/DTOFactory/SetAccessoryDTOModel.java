package it.unisalento.se.saw.models.DTOFactory;

import it.unisalento.se.saw.domain.Accessory;
import it.unisalento.se.saw.dto.AccessoryDTO;
import it.unisalento.se.saw.models.AbstractFactory;
import it.unisalento.se.saw.models.FactoryProducer;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class SetAccessoryDTOModel implements DTO<List<Accessory>, List<AccessoryDTO>> {
    @Override
    public List<AccessoryDTO> create(List<Accessory> accessories) {
        AbstractFactory abstractFactory = FactoryProducer.getFactory("DTO");
        DTO<Accessory, AccessoryDTO> dto = abstractFactory.getDTO("Accessory");
        List<AccessoryDTO> accessoryDTOS = new ArrayList<>();
        for (Accessory accessory: accessories) {
            accessoryDTOS.add(dto.create(accessory));
        }
        return accessoryDTOS;
    }
}
