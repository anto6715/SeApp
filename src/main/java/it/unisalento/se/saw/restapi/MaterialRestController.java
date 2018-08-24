package it.unisalento.se.saw.restapi;


import it.unisalento.se.saw.Iservices.IMaterialServices;
import it.unisalento.se.saw.domain.Material;
import it.unisalento.se.saw.dto.MaterialDTO;
import it.unisalento.se.saw.exceptions.MaterialNotFoundException;
import it.unisalento.se.saw.exceptions.TeachingNotFoundException;
import it.unisalento.se.saw.models.AbstractFactory;
import it.unisalento.se.saw.models.DTO;
import it.unisalento.se.saw.models.FactoryProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/material")
public class MaterialRestController {

    @Autowired
    IMaterialServices materialServices;

    public MaterialRestController() {
        super();
    }

    public MaterialRestController(IMaterialServices materialServices) {
        this.materialServices = materialServices;
    }

    @RequestMapping(value = "/getAll", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Material> getAll() {
        return materialServices.getAll();
    }

    @PostMapping(value = "/save", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Material save(@RequestBody MaterialDTO materialDTO) throws TeachingNotFoundException {
        try {
            return materialServices.save(materialDTO);
        } catch (Exception e) {
            throw new TeachingNotFoundException();
        }
    }

    @RequestMapping(value = "/getById/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public MaterialDTO getById(@PathVariable int id) throws MaterialNotFoundException {
        try {
            AbstractFactory abstractFactory = FactoryProducer.getFactory("DTO");
            DTO<Material, MaterialDTO> dto = abstractFactory.getDTO("MATERIAL");
            Material material = materialServices.getById(id);
            return dto.create(material);
        } catch (Exception e) {
            throw new MaterialNotFoundException();
        }
    }

    @RequestMapping(value = "/getByIdLesson/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Set<MaterialDTO> getByIdLesson(@PathVariable int id){

        AbstractFactory abstractFactory = FactoryProducer.getFactory("DTO");
        DTO<List<Material>, Set<MaterialDTO>> dto = abstractFactory.getDTO("SETMATERIAL");
        List<Material> materials = materialServices.getByIdLesson(id);
        return dto.create(materials);
    }

    @RequestMapping(value = "/getByIdTeaching/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Set<MaterialDTO> getByIdTeaching(@PathVariable int id){

        AbstractFactory abstractFactory = FactoryProducer.getFactory("DTO");
        DTO<List<Material>, Set<MaterialDTO>> dto = abstractFactory.getDTO("SETMATERIAL");
        List<Material> materials = materialServices.getByIdTeaching(id);
        return dto.create(materials);
    }
}
