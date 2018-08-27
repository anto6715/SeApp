package it.unisalento.se.saw.restapi;


import it.unisalento.se.saw.Iservices.IReviewTypeServices;
import it.unisalento.se.saw.domain.ReviewType;
import it.unisalento.se.saw.dto.ReviewTypeDTO;
import it.unisalento.se.saw.exceptions.ReviewTypeNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reviewType")
public class ReviewTypeRestController {

    @Autowired
    IReviewTypeServices reviewTypeServices;

    public ReviewTypeRestController() {
        super();
    }

    public ReviewTypeRestController(IReviewTypeServices reviewTypeServices) {
        this.reviewTypeServices = reviewTypeServices;
    }

    @RequestMapping(value = "/getAll", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<ReviewType> getAll() {
        return reviewTypeServices.getAll();
    }

    @PostMapping(value = "/save", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ReviewType save(@RequestBody ReviewTypeDTO reviewTypeDTO) {
        return reviewTypeServices.save(reviewTypeDTO);
    }

    @RequestMapping(value = "/getById/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ReviewType getById(@PathVariable int id) throws ReviewTypeNotFoundException {
        try {
            return reviewTypeServices.getById(id);
        } catch (Exception e) {
            throw new ReviewTypeNotFoundException();
        }
    }


}
