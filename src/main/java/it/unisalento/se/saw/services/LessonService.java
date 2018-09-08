package it.unisalento.se.saw.services;

import it.unisalento.se.saw.Iservices.ILessonServices;
import it.unisalento.se.saw.Iservices.IRoomServices;
import it.unisalento.se.saw.Iservices.ITeachingServices;
import it.unisalento.se.saw.domain.Lesson;
import it.unisalento.se.saw.domain.LessonId;
import it.unisalento.se.saw.domain.Room;
import it.unisalento.se.saw.domain.Teaching;
import it.unisalento.se.saw.dto.LessonDTO;
import it.unisalento.se.saw.dto.TeachingDTO;
import it.unisalento.se.saw.exceptions.LessonNotFoundException;
import it.unisalento.se.saw.exceptions.RoomNotFoundException;
import it.unisalento.se.saw.exceptions.TeachingNotFoundException;
import it.unisalento.se.saw.models.AbstractFactory;
import it.unisalento.se.saw.models.DTOFactory.DTO;
import it.unisalento.se.saw.models.DomainFactory.Domain;
import it.unisalento.se.saw.models.FactoryProducer;
import it.unisalento.se.saw.repositories.LessonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Set;

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

    @Transactional(readOnly = true)
    public Set<LessonDTO> getAll() {
        DTO<List<Lesson>, Set<LessonDTO>> dto = dtoFactory.getDTO("SETLESSON");
        return dto.create(lessonRepository.findAll());
    }

    @Transactional
    public Set<LessonDTO> getByDate(Date date, int id) {
        DTO<List<Lesson>, Set<LessonDTO>> dto = dtoFactory.getDTO("SETLESSON");
        return dto.create(lessonRepository.findLessonsByDateAndId_TeachingCourseIdCourse(date, id));
    }

    @Transactional
    public Set<LessonDTO> getByDateAndIdProfessor(Date date, int id) {
        DTO<List<Lesson>, Set<LessonDTO>> dto = dtoFactory.getDTO("SETLESSON");
        return dto.create(lessonRepository.findLessonsByDateAndId_TeachingProfessorIdProfessor(date,id));
    }

    @Transactional
    public LessonDTO getById(int id) throws LessonNotFoundException {
        try {
            DTO<Lesson,LessonDTO> dto = dtoFactory.getDTO("LESSON");
            return dto.create(lessonRepository.findLessonById_IdLesson(id));
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

    public Set<LessonDTO> getByRoom(int id) {
        DTO<List<Lesson>, Set<LessonDTO>> dto = dtoFactory.getDTO("SETLESSON");
        return dto.create(lessonRepository.findLessonsById_RoomIdRoom(id));
    }

    @Transactional
    public LessonDTO update(LessonDTO lessonDTO) throws RoomNotFoundException, TeachingNotFoundException {

        DTO<Lesson, LessonDTO> dto = dtoFactory.getDTO("Lesson");

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

        return dto.create(lessonRepository.save(updateLesson));
    }

    @Transactional
    public LessonDTO save(LessonDTO lessonDTO) throws RoomNotFoundException, TeachingNotFoundException {

        Domain<TeachingDTO, Teaching> domainTeaching = domainFactory.getDomain("Teaching");
        DTO<Lesson, LessonDTO> dto = dtoFactory.getDTO("Lesson");


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
        return dto.create(lessonRepository.save(lesson));


    }
}
