package it.unisalento.se.saw.models.DTOFactory;

import it.unisalento.se.saw.domain.Exam;
import it.unisalento.se.saw.dto.ExamDTO;

public class ExamDTOModel implements DTO<Exam, ExamDTO> {
    @Override
    public ExamDTO create(Exam exam) {
        ExamDTO examDTO = new ExamDTO();
        examDTO.setDate(exam.getData());
        examDTO.setId(exam.getId().getIdExam());
        examDTO.setIdRoom(exam.getId().getRoomIdRoom());
        examDTO.setIdTeaching(exam.getId().getTeachingIdTeaching());
        examDTO.setTime(exam.getTime());
        return examDTO;
    }
}
