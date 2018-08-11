package it.unisalento.se.saw.models;

import it.unisalento.se.saw.domain.Material;
import it.unisalento.se.saw.dto.MaterialDTO;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class SetMaterialDTOModel implements DTO<List<Material>, Set<MaterialDTO>> {
    @Override
    public Set<MaterialDTO> create(List<Material> materials) {
        AbstractFactory abstractFactory = FactoryProducer.getFactory("DTO");
        DTO<Material, MaterialDTO> dto = abstractFactory.getDTO("Material");
        Set<MaterialDTO> materialDTOS = new HashSet<>(0);
        for (Material material: materials){
            materialDTOS.add(dto.create(material));
        }
        return materialDTOS;
    }
}
