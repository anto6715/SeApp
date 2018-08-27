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

    public LessonRestController() {
        super();
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
            Lesson lesson = lessonServices.getById(id);
            System.out.println(lesson.getDate());
            AbstractFactory abstractFactory = FactoryProducer.getFactory("DTO");
            DTO<Lesson,LessonDTO> dto = abstractFactory.getDTO("LESSON");
            return dto.create(lesson);
        } catch (Exception e) {
            throw new LessonNotFoundException();
        }
    }

    @RequestMapping(value = "/getByDate/{date}_{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE, headers = "Accept=application/json")
    public Set<LessonDTO> getByDate(@PathVariable("date") String date, @PathVariable("id") int id) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date dateObj = sdf.parse(date);
        AbstractFactory abstractFactory = FactoryProducer.getFactory("DTO");
        DTO<List<Lesson>, Set<LessonDTO>> dto = abstractFactory.getDTO("SETLESSON");
        List<Lesson> lessons = lessonServices.getByDate(dateObj,id);
        return dto.create(lessons);


    }

    @RequestMapping(value = "/getByDateAndIdProf/{date}_{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE, headers = "Accept=application/json")
    public Set<LessonDTO> getByDateAndIdProfessor(@PathVariable("date") String date, @PathVariable("id") int id) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date dateObj = sdf.parse(date);
        AbstractFactory abstractFactory = FactoryProducer.getFactory("DTO");
        DTO<List<Lesson>, Set<LessonDTO>> dto = abstractFactory.getDTO("SETLESSON");
        List<Lesson> lessons = lessonServices.getByDateAndIdProfessor(dateObj,id);
        return dto.create(lessons);


    }
}
