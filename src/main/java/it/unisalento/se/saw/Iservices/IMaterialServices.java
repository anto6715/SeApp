package it.unisalento.se.saw.Iservices;

import it.unisalento.se.saw.domain.Material;
import it.unisalento.se.saw.dto.MaterialDTO;
import it.unisalento.se.saw.exceptions.MaterialNotFoundException;

import java.util.Set;

public interface IMaterialServices {

    public Set<MaterialDTO> getAll();
    public Set<MaterialDTO> getByIdLesson(int id);
    public Set<MaterialDTO> getByIdTeaching(int id);
    public MaterialDTO getById(int id) throws MaterialNotFoundException;
    public Material getDomainById(int id) throws MaterialNotFoundException;
    public MaterialDTO save(MaterialDTO materialDTO);
}
