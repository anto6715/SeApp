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
import it.unisalento.se.saw.repositories.LessonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class LessonService implements ILessonServices {

    @Autowired
    LessonRepository lessonRepository;

    @Autowired
    ITeachingServices teachingServices;

    @Autowired
    IRoomServices roomServices;

    @Transactional(readOnly = true)
    public List<Lesson> getAll() {
        return lessonRepository.findAll();
    }

    public List<Lesson> getByDay(String day) {
        return null;
    }

    public Lesson getById(int id) throws LessonNotFoundException {
        return null;
    }

    public List<Lesson> getByRoom(int id) {
        return null;
    }

    public Lesson save(LessonDTO lessonDTO) throws RoomNotFoundException, TeachingNotFoundException {
        Teaching teaching = teachingServices.getById(lessonDTO.getIdTeaching());
        Room room = roomServices.getById(lessonDTO.getIdRoom());

        LessonId lessonId = new LessonId();
        lessonId.setTeachingIdTeaching(teaching.getId().getIdTeaching());
        lessonId.setTeachingCourseIdCourse(teaching.getId().getCourseIdCourse());
        lessonId.setTeachingProfessorIdProfessor(teaching.getId().getProfessorIdProfessor());
        lessonId.setTeachingProfessorUserIdUser(teaching.getId().getProfessorUserIdUser());


        Lesson lesson = new Lesson();
        lesson.setDay(lessonDTO.getDay());
        lesson.setDuration(lessonDTO.getDuration());
        lesson.setTime(lessonDTO.getTime());
        lesson.setTeaching(teaching);
        lesson.setId(lessonId);

        Lesson lessonTemp = lessonRepository.save(lesson);


        Lesson lessonHasCourse = lessonRepository.findLessonByDayAndTime(lessonTemp.getDay(), lessonTemp.getTime());
        System.out.println("ciao");
        System.out.println(lessonHasCourse.getId().getIdLesson());
        System.out.println(lessonHasCourse.getId().getTeachingIdTeaching());
        System.out.println(lessonHasCourse.getId().getTeachingProfessorIdProfessor());
        System.out.println(lessonHasCourse.getId().getTeachingProfessorUserIdUser());
        System.out.println(lessonHasCourse.getId().getTeachingCourseIdCourse());
        return lessonRepository.save(lessonHasCourse);


    }
    public void remove(int id) throws LessonNotFoundException {

    }
}
