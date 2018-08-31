package it.unisalento.se.saw.restapi;

import it.unisalento.se.saw.Iservices.ILessonServices;
import it.unisalento.se.saw.domain.Lesson;
import it.unisalento.se.saw.dto.LessonDTO;
import it.unisalento.se.saw.exceptions.LessonNotFoundException;
import it.unisalento.se.saw.exceptions.RoomNotFoundException;
import it.unisalento.se.saw.exceptions.TeachingNotFoundException;
import it.unisalento.se.saw.models.AbstractFactory;
import it.unisalento.se.saw.models.DTOFactory.DTO;
import it.unisalento.se.saw.models.FactoryProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/lesson")
public class LessonRestController {

    @Autowired
    ILessonServices lessonServices;

    AbstractFactory abstractDTOFactory;

    public LessonRestController() {
        super();
        this.abstractDTOFactory = FactoryProducer.getFactory("DTO");
    }

    public LessonRestController(ILessonServices lessonServices) {
        this.lessonServices = lessonServices;
    }

    @RequestMapping(value = "/getAll", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Lesson> getAll() {
        return lessonServices.getAll();
    }

    @PostMapping(value = "/save", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Lesson save(@RequestBody LessonDTO lessonDTO) throws RoomNotFoundException, TeachingNotFoundException {
        try {
            System.out.println(lessonDTO.getStart());
            return lessonServices.save(lessonDTO);
        } catch (Exception e) {
            throw new RoomNotFoundException();
        }
    }

    @RequestMapping(value = "/getById/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public LessonDTO getById(@PathVariable int id) throws LessonNotFoundException {
        try {
            DTO<Lesson,LessonDTO> dto = this.abstractDTOFactory.getDTO("LESSON");
            return dto.create(lessonServices.getById(id));
        } catch (Exception e) {
            throw new LessonNotFoundException();
        }
    }

    @RequestMapping(value = "/getByDate/{date}_{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE, headers = "Accept=application/json")
    public Set<LessonDTO> getByDate(@PathVariable("date") String date, @PathVariable("id") int id) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date dateObj = sdf.parse(date);
        DTO<List<Lesson>, Set<LessonDTO>> dto = this.abstractDTOFactory.getDTO("SETLESSON");
        return dto.create(lessonServices.getByDate(dateObj,id));
    }

    @RequestMapping(value = "/getByDateAndIdProf/{date}_{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE, headers = "Accept=application/json")
    public Set<LessonDTO> getByDateAndIdProfessor(@PathVariable("date") String date, @PathVariable("id") int id) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date dateObj = sdf.parse(date);
        DTO<List<Lesson>, Set<LessonDTO>> dto = this.abstractDTOFactory.getDTO("SETLESSON");
        return dto.create(lessonServices.getByDateAndIdProfessor(dateObj,id));


    }
}
