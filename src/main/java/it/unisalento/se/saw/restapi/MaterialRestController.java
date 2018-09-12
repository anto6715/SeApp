package it.unisalento.se.saw.restapi;


import it.unisalento.se.saw.Iservices.IMaterialServices;
import it.unisalento.se.saw.domain.Material;
import it.unisalento.se.saw.dto.MaterialDTO;
import it.unisalento.se.saw.exceptions.MaterialNotFoundException;
import it.unisalento.se.saw.exceptions.TeachingNotFoundException;
import it.unisalento.se.saw.models.AbstractFactory;
import it.unisalento.se.saw.models.DTOFactory.DTO;
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


    @RequestMapping(value = "/getAll", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<MaterialDTO> getAll() {
        return materialServices.getAll();
    }

    @PostMapping(value = "/save", consumes = MediaType.APPLICATION_JSON_VALUE)
    public MaterialDTO save(@RequestBody MaterialDTO materialDTO) throws TeachingNotFoundException {
        return materialServices.save(materialDTO);
    }

    @RequestMapping(value = "/getById/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public MaterialDTO getById(@PathVariable int id) throws MaterialNotFoundException {
        return materialServices.getById(id);
    }

    @RequestMapping(value = "/getByIdLesson/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<MaterialDTO> getByIdLesson(@PathVariable int id){
        return materialServices.getByIdLesson(id);
    }

    @RequestMapping(value = "/getByIdTeaching/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<MaterialDTO> getByIdTeaching(@PathVariable int id){
        return materialServices.getByIdTeaching(id);
    }
}
