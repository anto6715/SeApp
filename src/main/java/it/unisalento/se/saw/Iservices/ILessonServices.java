package it.unisalento.se.saw.Iservices;

import it.unisalento.se.saw.domain.Lesson;
import it.unisalento.se.saw.dto.LessonDTO;
import it.unisalento.se.saw.exceptions.LessonNotFoundException;
import it.unisalento.se.saw.exceptions.RoomNotFoundException;
import it.unisalento.se.saw.exceptions.TeachingNotFoundException;

import java.util.Date;
import java.util.List;
import java.util.Set;

public interface ILessonServices {

    public List<LessonDTO> getAll();
    public List<LessonDTO> getByDate(Date date, int id);
    public List<LessonDTO> getByDateAndIdProfessor(Date date, int id);
    public LessonDTO getById(int id) throws LessonNotFoundException;
    public Lesson getDomainById(int id) throws LessonNotFoundException;
    public List<LessonDTO> getByRoom(int id);
    public List<LessonDTO> getByTeaching(int id);
    public List<LessonDTO> getByProfessor(int id);
    public LessonDTO save(LessonDTO lessonDTO) throws TeachingNotFoundException, RoomNotFoundException;
    public LessonDTO update(LessonDTO lessonDTO) throws TeachingNotFoundException, RoomNotFoundException;
}
