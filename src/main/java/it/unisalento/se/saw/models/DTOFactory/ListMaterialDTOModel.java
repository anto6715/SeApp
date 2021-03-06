package it.unisalento.se.saw.models.DTOFactory;

import it.unisalento.se.saw.domain.Material;
import it.unisalento.se.saw.dto.MaterialDTO;
import it.unisalento.se.saw.models.AbstractFactory;
import it.unisalento.se.saw.models.FactoryProducer;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ListMaterialDTOModel implements DTO<List<Material>, List<MaterialDTO>> {
    @Override
    public List<MaterialDTO> create(List<Material> materials) {
        AbstractFactory abstractFactory = FactoryProducer.getFactory("DTO");
        DTO<Material, MaterialDTO> dto = abstractFactory.getDTO("Material");
        List<MaterialDTO> materialDTOS = new ArrayList<>(0);
        for (Material material: materials){
            materialDTOS.add(dto.create(material));
        }
        return materialDTOS;
    }
}
