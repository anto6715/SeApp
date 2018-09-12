package it.unisalento.se.saw.Iservices;

import it.unisalento.se.saw.dto.ExamDTO;
import it.unisalento.se.saw.exceptions.ExamNotFoundException;
import it.unisalento.se.saw.exceptions.RoomNotFoundException;
import it.unisalento.se.saw.exceptions.TeachingNotFoundException;

import java.util.List;
import java.util.Set;

public interface IExamServices {

    public List<ExamDTO> getAll();
    public ExamDTO getById(int id) throws ExamNotFoundException;
    public ExamDTO save(ExamDTO examDTO);
}
