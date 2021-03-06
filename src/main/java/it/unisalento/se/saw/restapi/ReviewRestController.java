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

    @PostMapping(value = "/save", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ReviewDTO save(@RequestBody ReviewDTO reviewDTO) throws StudentNotFoundException, ReviewTypeNotFoundException {
        return reviewServices.save(reviewDTO);
    }

    @RequestMapping(value = "/getById/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ReviewDTO getById(@PathVariable int id) {
      try {
          return reviewServices.getById(id);
      } catch (ReviewNotFoundException e) {
          System.out.println(e.getMessage());
          return null;
      }

    }

    @RequestMapping(value = "/getByIdLesson/{idLesson}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<ReviewDTO> getByIdLesson(@PathVariable int idLesson) {
        return reviewServices.getByIdLesson(idLesson);
    }

    @RequestMapping(value = "/getByIdMaterial/{idMaterial}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<ReviewDTO> getByIdMaterial(@PathVariable int idMaterial) {
        return reviewServices.getByIdMaterial(idMaterial);
    }

    @RequestMapping(value = "/getByIdStudentAndIdMaterial/{idStudent}_{idMaterial}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ReviewDTO getByIdStudentAndIdMaterial(@PathVariable("idStudent") int idStudent, @PathVariable("idMaterial") int idMaterial) {
        try {
            return reviewServices.getByIdStudentAndIdMaterial(idStudent,idMaterial);
        } catch (ReviewNotFoundException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    @RequestMapping(value = "/getByIdStudentAndIdLesson/{idStudent}_{idLesson}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ReviewDTO getByIdStudentAndIdLesson(@PathVariable("idStudent") int idStudent, @PathVariable("idLesson") int idLesson) {
        try {
            return reviewServices.getByIdStudentAndIdLesson(idStudent,idLesson);
        } catch (ReviewNotFoundException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }



}
