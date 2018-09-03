package it.unisalento.se.saw.Iservices;

import it.unisalento.se.saw.domain.Lesson;
import it.unisalento.se.saw.dto.LessonDTO;
import it.unisalento.se.saw.exceptions.LessonNotFoundException;
import it.unisalento.se.saw.exceptions.RoomNotFoundException;
import it.unisalento.se.saw.exceptions.TeachingNotFoundException;

import java.util.Date;
import java.util.Set;

public interface ILessonServices {

    public Set<LessonDTO> getAll();
    public Set<LessonDTO> getByDate(Date date, int id);
    public Set<LessonDTO> getByDateAndIdProfessor(Date date, int id);
    public LessonDTO getById(int id) throws LessonNotFoundException;
    public Lesson getDomainById(int id) throws LessonNotFoundException;
    public Set<LessonDTO> getByRoom(int id);
    public LessonDTO save(LessonDTO lessonDTO) throws TeachingNotFoundException, RoomNotFoundException;
}
