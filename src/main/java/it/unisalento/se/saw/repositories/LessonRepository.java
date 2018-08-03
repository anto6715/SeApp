package it.unisalento.se.saw.repositories;

import it.unisalento.se.saw.domain.Lesson;
import it.unisalento.se.saw.domain.LessonId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LessonRepository extends JpaRepository<Lesson, LessonId> {

    public Lesson findLessonByDayAndTime(String day, String time);
}
