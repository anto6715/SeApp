package it.unisalento.se.saw.Iservices;

import it.unisalento.se.saw.domain.Lesson;
import it.unisalento.se.saw.dto.LessonDTO;
import it.unisalento.se.saw.exceptions.LessonNotFoundException;
import it.unisalento.se.saw.exceptions.RoomNotFoundException;
import it.unisalento.se.saw.exceptions.TeachingNotFoundException;

import java.util.List;

public interface ILessonServices {

    public List<Lesson> getAll();
    public Lesson getById(int id) throws LessonNotFoundException;
    public List<Lesson> getByRoom(int id);
    public Lesson save(LessonDTO lessonDTO) throws TeachingNotFoundException, RoomNotFoundException;
    public void remove(int id) throws LessonNotFoundException;
}
