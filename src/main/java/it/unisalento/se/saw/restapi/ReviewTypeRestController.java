package it.unisalento.se.saw.restapi;


import it.unisalento.se.saw.Iservices.IReviewTypeServices;
import it.unisalento.se.saw.domain.ReviewType;
import it.unisalento.se.saw.dto.ReviewTypeDTO;
import it.unisalento.se.saw.exceptions.ReviewTypeNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/reviewType")
public class ReviewTypeRestController {

    @Autowired
    IReviewTypeServices reviewTypeServices;


    @RequestMapping(value = "/getAll", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Set<ReviewTypeDTO> getAll() {
        return reviewTypeServices.getAll();
    }

    @PostMapping(value = "/save", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ReviewTypeDTO save(@RequestBody ReviewTypeDTO reviewTypeDTO) {
        return reviewTypeServices.save(reviewTypeDTO);
    }

    @RequestMapping(value = "/getById/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ReviewTypeDTO getById(@PathVariable int id) throws ReviewTypeNotFoundException {
        return reviewTypeServices.getById(id);
    }


}
