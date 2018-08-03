package it.unisalento.se.saw.restapi;

import it.unisalento.se.saw.Iservices.ILessonServices;
import it.unisalento.se.saw.domain.Lesson;
import it.unisalento.se.saw.dto.LessonDTO;
import it.unisalento.se.saw.exceptions.LessonNotFoundException;
import it.unisalento.se.saw.exceptions.RoomNotFoundException;
import it.unisalento.se.saw.exceptions.TeachingNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
            return lessonServices.save(lessonDTO);
        } catch (Exception e) {
            throw new RoomNotFoundException();
        }
    }

    @RequestMapping(value = "/getByDay/{day}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Lesson> getByDay(@PathVariable String day) {
        return lessonServices.getByDay(day);

    }

    @RequestMapping(value = "/getById/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Lesson getById(@PathVariable int id) throws LessonNotFoundException {
        try {
            return lessonServices.getById(id);
        } catch (Exception e) {
            throw new LessonNotFoundException();
        }
    }
}
