package it.unisalento.se.saw.restapi;


import it.unisalento.se.saw.Iservices.IReviewServices;
import it.unisalento.se.saw.domain.Review;
import it.unisalento.se.saw.dto.ReviewDTO;
import it.unisalento.se.saw.exceptions.ReviewNotFoundException;
import it.unisalento.se.saw.exceptions.ReviewTypeNotFoundException;
import it.unisalento.se.saw.exceptions.StudentNotFoundException;
import it.unisalento.se.saw.models.AbstractFactory;
import it.unisalento.se.saw.models.DTOFactory.DTO;
import it.unisalento.se.saw.models.FactoryProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/review")
public class ReviewRestController {

    @Autowired
    IReviewServices reviewServices;

    AbstractFactory abstractDTOFactory;

    public ReviewRestController() {
        super();
        this.abstractDTOFactory = FactoryProducer.getFactory("DTO");
    }

    public ReviewRestController(IReviewServices reviewServices) {
        this.reviewServices = reviewServices;
    }

    @PostMapping(value = "/save", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Review save(@RequestBody ReviewDTO reviewDTO) throws StudentNotFoundException, ReviewTypeNotFoundException {
        return reviewServices.save(reviewDTO);
    }

    @RequestMapping(value = "/getById/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ReviewDTO getById(@PathVariable int id) throws ReviewNotFoundException {
        try {
            DTO<Review, ReviewDTO> dto = this.abstractDTOFactory.getDTO("Review");
            return dto.create(reviewServices.getById(id));
        } catch (Exception e) {
            throw new ReviewNotFoundException();
        }
    }

    @RequestMapping(value = "/getByIdLesson/{idLesson}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Set<ReviewDTO> getByIdLesson(@PathVariable int idLesson) throws ReviewNotFoundException {
        try {
            DTO<List<Review>, Set<ReviewDTO> > dto = this.abstractDTOFactory.getDTO("SETREVIEW");
            return dto.create(reviewServices.getByIdLesson(idLesson));
        } catch (Exception e) {
            throw new ReviewNotFoundException();
        }
    }

    @RequestMapping(value = "/getByIdMaterial/{idMaterial}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Set<ReviewDTO> getByIdMaterial(@PathVariable int idMaterial) throws ReviewNotFoundException {
        try {
            DTO<List<Review>, Set<ReviewDTO> > dto = this.abstractDTOFactory.getDTO("SETREVIEW");
            return dto.create(reviewServices.getByIdMaterial(idMaterial));
        } catch (Exception e) {
            throw new ReviewNotFoundException();
        }
    }

    @RequestMapping(value = "/getByIdStudentAndIdMaterial/{idStudent}_{idMaterial}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ReviewDTO getByIdStudentAndIdMaterial(@PathVariable("idStudent") int idStudent, @PathVariable("idMaterial") int idMaterial) throws ReviewNotFoundException {
        try {
            DTO<Review, ReviewDTO> dto = this.abstractDTOFactory.getDTO("Review");
            return dto.create(reviewServices.getByIdStudentAndIdMaterial(idStudent,idMaterial));
        } catch (Exception e) {
            throw new ReviewNotFoundException();
        }

    }

    @RequestMapping(value = "/getByIdStudentAndIdLesson/{idStudent}_{idLesson}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ReviewDTO getByIdStudentAndIdLesson(@PathVariable("idStudent") int idStudent, @PathVariable("idLesson") int idLesson) throws ReviewNotFoundException {
        try {
            DTO<Review, ReviewDTO> dto = this.abstractDTOFactory.getDTO("Review");
            return dto.create(reviewServices.getByIdStudentAndIdLesson(idStudent,idLesson));
        } catch (Exception e) {
            throw new ReviewNotFoundException();
        }

    }



}
