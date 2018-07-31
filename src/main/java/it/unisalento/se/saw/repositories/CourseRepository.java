package it.unisalento.se.saw.repositories;

import it.unisalento.se.saw.domain.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseRepository extends JpaRepository<Course, Integer> {

    @Query("select c from Course c where c.name=:name ")
    public Course getCourseByName(@Param("name") String name);
}
