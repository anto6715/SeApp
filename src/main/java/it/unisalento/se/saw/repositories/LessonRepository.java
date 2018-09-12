package it.unisalento.se.saw.repositories;

import it.unisalento.se.saw.domain.Lesson;
import it.unisalento.se.saw.domain.LessonId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface LessonRepository extends JpaRepository<Lesson, LessonId> {


    public Lesson findLessonById_IdLesson(int id);
    public List<Lesson> findLessonsByDateAndId_TeachingCourseIdCourse(Date date, int id);
    public List<Lesson> findLessonsByDateAndId_TeachingProfessorIdProfessor(Date date, int id);
    public List<Lesson> findLessonsByDate(Date date);
    public List<Lesson> findLessonsById_RoomIdRoom(int id);
    public List<Lesson> findLessonById_TeachingIdTeaching(int id);
    public List<Lesson> findLessonById_TeachingProfessorIdProfessorOrderByDateAsc(int id);
}
