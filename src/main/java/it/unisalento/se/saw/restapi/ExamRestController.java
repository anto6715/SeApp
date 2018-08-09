package it.unisalento.se.saw.restapi;

import it.unisalento.se.saw.Iservices.IExamServices;
import it.unisalento.se.saw.domain.Exam;
import it.unisalento.se.saw.dto.ExamDTO;
import it.unisalento.se.saw.exceptions.ExamNotFoundException;
import it.unisalento.se.saw.exceptions.RoomNotFoundException;
import it.unisalento.se.saw.exceptions.TeachingNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/exam")
public class ExamRestController {

    @Autowired
    IExamServices examServices;

    public ExamRestController() {
        super();
    }

    public ExamRestController(IExamServices examServices) {
        this.examServices= examServices;
    }

    @RequestMapping(value = "/getAll", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Exam> getAll() {
        return examServices.getAll();
    }

    @PostMapping(value = "/save", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Exam save(@RequestBody ExamDTO examDTO) throws RoomNotFoundException, TeachingNotFoundException {
        examDTO.getTime().setHours(examDTO.getTime().getHours()-1); // altrimenti ha un'ora in più
        System.out.println(examDTO.getDate());
        System.out.println(examDTO.getTime());
        return null;//examServices.save(examDTO);
    }

    @RequestMapping(value = "/getById/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Exam getById(@PathVariable int id) throws ExamNotFoundException {
        try {
            return examServices.getById(id);
        } catch (Exception e) {
            throw new ExamNotFoundException();
        }
    }
}
