package it.unisalento.se.saw.models.DTOFactory;

import it.unisalento.se.saw.domain.Material;
import it.unisalento.se.saw.dto.MaterialDTO;

public class MaterialDTOModel implements DTO<Material, MaterialDTO> {
    @Override
    public MaterialDTO create(Material material) {
        MaterialDTO materialDTO = new MaterialDTO();
        materialDTO.setId(material.getId().getIdMaterial());
        materialDTO.setIdLesson(material.getId().getLessonIdLesson());
        materialDTO.setLink(material.getLink());
        materialDTO.setName(material.getName());
        materialDTO.setDate(material.getLesson().getDate().toString());
        materialDTO.setIdUserProf(material.getLesson().getTeaching().getProfessor().getId().getUserIdUser());

        return materialDTO;
    }
}
