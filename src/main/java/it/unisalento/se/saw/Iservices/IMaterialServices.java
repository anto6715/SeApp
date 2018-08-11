package it.unisalento.se.saw.Iservices;

import it.unisalento.se.saw.domain.Material;
import it.unisalento.se.saw.dto.MaterialDTO;
import it.unisalento.se.saw.exceptions.MaterialNotFoundException;
import it.unisalento.se.saw.exceptions.TeachingNotFoundException;

import java.util.List;

public interface IMaterialServices {

    public List<Material> getAll();
    public List<Material> getByIdLesson(int id);
    public Material getById(int id) throws MaterialNotFoundException;
    public Material save(MaterialDTO materialDTO) throws TeachingNotFoundException;
    public void remove(int id) throws MaterialNotFoundException;
}
