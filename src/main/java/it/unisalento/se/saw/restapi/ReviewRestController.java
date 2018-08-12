package it.unisalento.se.saw.restapi;


import it.unisalento.se.saw.Iservices.IReviewServices;
import it.unisalento.se.saw.domain.Review;
import it.unisalento.se.saw.dto.ReviewDTO;
import it.unisalento.se.saw.exceptions.ReviewNotFoundException;
import it.unisalento.se.saw.exceptions.ReviewTypeNotFoundException;
import it.unisalento.se.saw.exceptions.StudentNotFoundException;
import it.unisalento.se.saw.models.AbstractFactory;
import it.unisalento.se.saw.models.DTO;
import it.unisalento.se.saw.models.FactoryProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/review")
public class ReviewRestController {

    @Autowired
    IReviewServices reviewServices;

    public ReviewRestController() {
        super();
    }

    public ReviewRestController(IReviewServices reviewServices) {
        this.reviewServices = reviewServices;
    }

    @RequestMapping(value = "/getAll", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Review> getAll() {
        return reviewServices.getAll();
    }

    @PostMapping(value = "/save", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Review save(@RequestBody ReviewDTO reviewDTO) throws StudentNotFoundException, ReviewTypeNotFoundException {
        return reviewServices.save(reviewDTO);
    }

    @RequestMapping(value = "/getById/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Review getById(@PathVariable int id) throws ReviewNotFoundException {
        try {
            return reviewServices.getById(id);
        } catch (Exception e) {
            throw new ReviewNotFoundException();
        }
    }

    @RequestMapping(value = "/getByType/{idType}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Review> getByType(@PathVariable int idType) {
        return reviewServices.getByType(idType);
    }

    @RequestMapping(value = "/getByIdStudentAndIdMaterial/{idStudent}_{idMaterial}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ReviewDTO getByIdStudentAndIdMaterial(@PathVariable("idStudent") int idStudent, @PathVariable("idMaterial") int idMaterial) throws ReviewNotFoundException {
        try {
            AbstractFactory abstractFactory = FactoryProducer.getFactory("DTO");
            DTO<Review, ReviewDTO> dto = abstractFactory.getDTO("Review");
            return dto.create(reviewServices.getByIdStudentAndIdMaterial(idStudent,idMaterial));
        } catch (Exception e) {
            throw new ReviewNotFoundException();
        }

    }

    @RequestMapping(value = "/getByIdStudentAndIdLesson/{idStudent}_{idLesson}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ReviewDTO getByIdStudentAndIdLesson(@PathVariable("idStudent") int idStudent, @PathVariable("idLesson") int idLesson) throws ReviewNotFoundException {
        try {
            AbstractFactory abstractFactory = FactoryProducer.getFactory("DTO");
            DTO<Review, ReviewDTO> dto = abstractFactory.getDTO("Review");
            return dto.create(reviewServices.getByIdStudentAndIdLesson(idStudent,idLesson));
        } catch (Exception e) {
            throw new ReviewNotFoundException();
        }

    }



}
