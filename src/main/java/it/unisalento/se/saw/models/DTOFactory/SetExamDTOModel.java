package it.unisalento.se.saw.models.DTOFactory;

import it.unisalento.se.saw.domain.Exam;
import it.unisalento.se.saw.dto.ExamDTO;
import it.unisalento.se.saw.models.AbstractFactory;
import it.unisalento.se.saw.models.FactoryProducer;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class SetExamDTOModel implements DTO<List<Exam>, Set<ExamDTO>> {
    @Override
    public Set<ExamDTO> create(List<Exam> exams) {
        AbstractFactory abstractFactory = FactoryProducer.getFactory("DTO");
        DTO<Exam, ExamDTO> dto = abstractFactory.getDTO("Exam");
        Set<ExamDTO> examDTOS = new HashSet<>(0);
        for (Exam exam: exams) {
            examDTOS.add(dto.create(exam));
        }
        return examDTOS;
    }
}
