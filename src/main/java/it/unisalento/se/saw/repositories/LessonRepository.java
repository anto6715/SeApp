package it.unisalento.se.saw.repositories;

import it.unisalento.se.saw.domain.Lesson;
import it.unisalento.se.saw.domain.LessonId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface LessonRepository extends JpaRepository<Lesson, LessonId> {


    public Lesson findLessonById_IdLesson(int id);
    public List<Lesson> findLessonsByDateAndId_TeachingCourseIdCourse(Date date, int id);
    public List<Lesson> findLessonsByDate(Date date);
}
