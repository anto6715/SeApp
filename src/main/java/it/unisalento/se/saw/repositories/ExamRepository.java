package it.unisalento.se.saw.repositories;

import it.unisalento.se.saw.domain.Exam;
import it.unisalento.se.saw.domain.ExamId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExamRepository  extends JpaRepository<Exam, Integer> {

    public Exam findExamById_IdExam(int id);
}
