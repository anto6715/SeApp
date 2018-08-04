package it.unisalento.se.saw.Iservices;

import it.unisalento.se.saw.domain.Exam;
import it.unisalento.se.saw.dto.ExamDTO;
import it.unisalento.se.saw.exceptions.ExamNotFoundException;
import it.unisalento.se.saw.exceptions.RoomNotFoundException;
import it.unisalento.se.saw.exceptions.TeachingNotFoundException;

import java.util.List;

public interface IExamServices {

    public List<Exam> getAll();
    public Exam getById(int id) throws ExamNotFoundException;
    public Exam save(ExamDTO examDTO) throws RoomNotFoundException, TeachingNotFoundException;
    public void remove(int id) throws ExamNotFoundException;
}
