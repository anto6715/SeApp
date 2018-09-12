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
import java.util.Set;

@RestController
@RequestMapping("/exam")
public class ExamRestController {

    @Autowired
    IExamServices examServices;

    public ExamRestController() {
        super();
    }


    @RequestMapping(value = "/getAll", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<ExamDTO> getAll() {
        return examServices.getAll();
    }

    @PostMapping(value = "/save", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ExamDTO save(@RequestBody ExamDTO examDTO) {
        return examServices.save(examDTO);
    }

    @RequestMapping(value = "/getById/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ExamDTO getById(@PathVariable int id) throws ExamNotFoundException {
        return examServices.getById(id);
    }
}
