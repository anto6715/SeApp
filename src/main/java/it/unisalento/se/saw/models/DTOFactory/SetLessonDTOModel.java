package it.unisalento.se.saw.models.DTOFactory;

import it.unisalento.se.saw.domain.Lesson;
import it.unisalento.se.saw.dto.LessonDTO;
import it.unisalento.se.saw.models.AbstractFactory;
import it.unisalento.se.saw.models.FactoryProducer;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class SetLessonDTOModel implements DTO<List<Lesson>, List<LessonDTO>> {
    @Override
    public List<LessonDTO> create(List<Lesson> lessons) {
        AbstractFactory abstractFactory = FactoryProducer.getFactory("DTO");
        DTO<Lesson,LessonDTO> dto = abstractFactory.getDTO("Lesson");
        List<LessonDTO> lessonDTOS = new ArrayList<>();
        for(Lesson lesson: lessons){
            lessonDTOS.add(dto.create(lesson));
        }
        return lessonDTOS;
    }
}
