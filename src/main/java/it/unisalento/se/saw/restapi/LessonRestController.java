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
import it.unisalento.se.saw.models.Strategy.Context;
import it.unisalento.se.saw.models.Strategy.DateParseStrategy;
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


    public LessonRestController(ILessonServices lessonServices) {
        this.lessonServices = lessonServices;
    }

    @RequestMapping(value = "/getAll", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<LessonDTO> getAll() {
        return lessonServices.getAll();
    }

    @PostMapping(value = "/save", consumes = MediaType.APPLICATION_JSON_VALUE)
    public LessonDTO save(@RequestBody LessonDTO lessonDTO) throws RoomNotFoundException, TeachingNotFoundException {
        return lessonServices.save(lessonDTO);
    }

    @PostMapping(value = "/update", consumes = MediaType.APPLICATION_JSON_VALUE)
    public LessonDTO update(@RequestBody LessonDTO lessonDTO) throws RoomNotFoundException, TeachingNotFoundException {
        return lessonServices.update(lessonDTO);
    }

    @RequestMapping(value = "/getById/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public LessonDTO getById(@PathVariable int id) throws LessonNotFoundException {
        return lessonServices.getById(id);
    }

    @RequestMapping(value = "/getByIdRoom/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<LessonDTO> getByIdRoom(@PathVariable int id) {
        return lessonServices.getByRoom(id);
    }

    @RequestMapping(value = "/getByIdTeaching/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<LessonDTO> getByIdTeaching(@PathVariable int id) {
        return lessonServices.getByTeaching(id);
    }

    @RequestMapping(value = "/getByIdProfessor/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<LessonDTO> getByIdProfessor(@PathVariable int id) {
        return lessonServices.getByProfessor(id);
    }

    @RequestMapping(value = "/getByDate/{date}_{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE, headers = "Accept=application/json")
    public List<LessonDTO> getByDateAndIdCourse(@PathVariable("date") String date, @PathVariable("id") int id)  {
        Context context = new Context((new DateParseStrategy()));
        Date dateObj= context.executeDateStrategy(date);
        return lessonServices.getByDate(dateObj,id);
    }

    @RequestMapping(value = "/getByDateAndIdProf/{date}_{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE, headers = "Accept=application/json")
    public List<LessonDTO> getByDateAndIdProfessor(@PathVariable("date") String date, @PathVariable("id") int id)  {
        Context context = new Context((new DateParseStrategy()));
        Date dateObj= context.executeDateStrategy(date);
        return lessonServices.getByDateAndIdProfessor(dateObj,id);
    }
}
