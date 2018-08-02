package it.unisalento.se.saw.repositories;

import it.unisalento.se.saw.domain.Teaching;
import it.unisalento.se.saw.domain.TeachingId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TeachingRepository extends JpaRepository<Teaching, TeachingId> {

    public Teaching findTeachingById_IdTeaching(int id);
    public Teaching findTeachingByName(String name);
    public List<Teaching> findTeachingsById_CourseIdCourse(int id);
}
