package it.unisalento.se.saw.repositories;

import it.unisalento.se.saw.domain.Student;
import it.unisalento.se.saw.domain.StudentId;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, StudentId> {


    Student findStudentById_IdStudent(int id);

    List<Student> findStudentsByCourse_IdCourse(int course);

    Student findStudentById_UserIdUser(int id);
}
