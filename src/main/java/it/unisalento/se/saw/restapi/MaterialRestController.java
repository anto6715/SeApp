package it.unisalento.se.saw.restapi;


import it.unisalento.se.saw.Iservices.IMaterialServices;
import it.unisalento.se.saw.domain.Material;
import it.unisalento.se.saw.dto.MaterialDTO;
import it.unisalento.se.saw.exceptions.MaterialNotFoundException;
import it.unisalento.se.saw.exceptions.TeachingNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public Material getById(@PathVariable int id) throws MaterialNotFoundException {
        try {
            return materialServices.getById(id);
        } catch (Exception e) {
            throw new MaterialNotFoundException();
        }
    }
}
