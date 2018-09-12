package it.unisalento.se.saw.services;

import it.unisalento.se.saw.Iservices.ILessonServices;
import it.unisalento.se.saw.Iservices.IRoomServices;
import it.unisalento.se.saw.Iservices.ITeachingServices;
import it.unisalento.se.saw.domain.Lesson;
import it.unisalento.se.saw.domain.LessonId;
import it.unisalento.se.saw.domain.Room;
import it.unisalento.se.saw.domain.Teaching;
import it.unisalento.se.saw.dto.LessonDTO;
import it.unisalento.se.saw.exceptions.LessonNotFoundException;
import it.unisalento.se.saw.exceptions.RoomNotFoundException;
import it.unisalento.se.saw.exceptions.TeachingNotFoundException;
import it.unisalento.se.saw.models.AbstractFactory;
import it.unisalento.se.saw.models.DTOFactory.DTO;
import it.unisalento.se.saw.models.FactoryProducer;
import it.unisalento.se.saw.repositories.LessonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class LessonService implements ILessonServices {

    @Autowired
    LessonRepository lessonRepository;

    @Autowired
    ITeachingServices teachingServices;

    @Autowired
    IRoomServices roomServices;

    AbstractFactory dtoFactory = FactoryProducer.getFactory("DTO");
    AbstractFactory domainFactory = FactoryProducer.getFactory("DOMAIN");
    DTO<List<Lesson>, List<LessonDTO>> listLessonDto = dtoFactory.getDTO("LISTLESSON");
    DTO<Lesson,LessonDTO> lessonDto = dtoFactory.getDTO("LESSON");

    @Transactional(readOnly = true)
    public List<LessonDTO> getAll() {
        return listLessonDto.create(lessonRepository.findAll());
    }

    @Transactional
    public List<LessonDTO> getByDate(Date date, int id) {
        return listLessonDto.create(lessonRepository.findLessonsByDateAndId_TeachingCourseIdCourse(date, id));
    }

    @Transactional
    public List<LessonDTO> getByDateAndIdProfessor(Date date, int id) {
        return listLessonDto.create(lessonRepository.findLessonsByDateAndId_TeachingProfessorIdProfessor(date,id));
    }

    @Transactional
    public LessonDTO getById(int id) throws LessonNotFoundException {
        try {
            return lessonDto.create(lessonRepository.findLessonById_IdLesson(id));
        } catch (Exception e) {
            throw new LessonNotFoundException();
        }
    }

    @Transactional
    public Lesson getDomainById(int id) throws LessonNotFoundException {
        try {
            return lessonRepository.findLessonById_IdLesson(id);
        } catch (Exception e) {
            throw new LessonNotFoundException();
        }
    }

    public List<LessonDTO> getByRoom(int id) {
        return listLessonDto.create(lessonRepository.findLessonsById_RoomIdRoom(id));
    }

    public List<LessonDTO> getByTeaching(int id) {
        return listLessonDto.create(lessonRepository.findLessonById_TeachingIdTeaching(id));
    }

    public List<LessonDTO> getByProfessor(int id) {
        return listLessonDto.create(lessonRepository.findLessonById_TeachingProfessorIdProfessorOrderByDateAsc(id));
    }

    @Transactional
    public LessonDTO update(LessonDTO lessonDTO) throws RoomNotFoundException, TeachingNotFoundException {

        Lesson lesson = lessonRepository.findLessonById_IdLesson(lessonDTO.getId());
        lessonRepository.delete(lesson);

        Lesson updateLesson = new Lesson();
        Room room = roomServices.getDomainById(lessonDTO.getIdRoom());
        Teaching teaching = teachingServices.getDomainById(lessonDTO.getIdTeaching());

        LessonId lessonId = new LessonId();
        lessonId.setRoomIdRoom(room.getIdRoom());
        lessonId.setTeachingIdTeaching(teaching.getId().getIdTeaching());
        lessonId.setTeachingCourseIdCourse(teaching.getId().getCourseIdCourse());
        lessonId.setTeachingProfessorIdProfessor(teaching.getId().getProfessorIdProfessor());
        lessonId.setTeachingProfessorUserIdUser(teaching.getId().getProfessorUserIdUser());

        updateLesson.setId(lessonId);
        updateLesson.setRoom(room);
        updateLesson.setTeaching(teaching);
        updateLesson.setDate(lessonDTO.getDate());
        updateLesson.setStart(lessonDTO.getStart());
        updateLesson.setEnd(lessonDTO.getEnd());

        return lessonDto.create(lessonRepository.save(updateLesson));
    }

    @Transactional
    public LessonDTO save(LessonDTO lessonDTO) throws RoomNotFoundException, TeachingNotFoundException {


        Teaching teaching =teachingServices.getDomainById(lessonDTO.getIdTeaching());
        Room room = roomServices.getDomainById(lessonDTO.getIdRoom());

        LessonId lessonId = new LessonId();
        lessonId.setTeachingIdTeaching(teaching.getId().getIdTeaching());
        lessonId.setTeachingCourseIdCourse(teaching.getId().getCourseIdCourse());
        lessonId.setTeachingProfessorIdProfessor(teaching.getId().getProfessorIdProfessor());
        lessonId.setTeachingProfessorUserIdUser(teaching.getId().getProfessorUserIdUser());
        lessonId.setRoomIdRoom(room.getIdRoom());



        Lesson lesson = new Lesson();

        lesson.setTeaching(teaching);
        lesson.setId(lessonId);
        lesson.setRoom(room);
        lesson.setDate(lessonDTO.getDate());
        lesson.setStart(lessonDTO.getStart());
        lesson.setEnd(lessonDTO.getEnd());
        return lessonDto.create(lessonRepository.save(lesson));
    }
}
