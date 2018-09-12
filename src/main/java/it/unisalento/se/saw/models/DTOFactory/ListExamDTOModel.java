package it.unisalento.se.saw.models.DTOFactory;

import it.unisalento.se.saw.domain.Exam;
import it.unisalento.se.saw.dto.ExamDTO;
import it.unisalento.se.saw.models.AbstractFactory;
import it.unisalento.se.saw.models.FactoryProducer;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ListExamDTOModel implements DTO<List<Exam>, List<ExamDTO>> {
    @Override
    public List<ExamDTO> create(List<Exam> exams) {
        AbstractFactory abstractFactory = FactoryProducer.getFactory("DTO");
        DTO<Exam, ExamDTO> dto = abstractFactory.getDTO("Exam");
        List<ExamDTO> examDTOS = new ArrayList<>();
        for (Exam exam: exams) {
            examDTOS.add(dto.create(exam));
        }
        return examDTOS;
    }
}
