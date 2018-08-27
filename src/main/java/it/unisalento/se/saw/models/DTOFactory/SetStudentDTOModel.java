package it.unisalento.se.saw.models.DTOFactory;

import it.unisalento.se.saw.domain.Student;
import it.unisalento.se.saw.dto.StudentDTO;
import it.unisalento.se.saw.models.AbstractFactory;
import it.unisalento.se.saw.models.FactoryProducer;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class SetStudentDTOModel implements DTO<List<Student>, Set<StudentDTO>> {
    @Override
    public Set<StudentDTO> create(List<Student> students) {
        AbstractFactory abstractFactory = FactoryProducer.getFactory("DTO");
        DTO<Student,StudentDTO> dto = abstractFactory.getDTO("Student");
        Set<StudentDTO> studentDTOS = new HashSet<>(0);
        for(Student student: students){
            studentDTOS.add(dto.create(student));
        }
        return studentDTOS;
    }
}