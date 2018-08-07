package it.unisalento.se.saw.models;

import it.unisalento.se.saw.domain.Student;
import it.unisalento.se.saw.dto.StudentDTO;

public class DtoFactory {

    public DTO getDTO(String dtoType){
        if(dtoType == null){
            return null;
        }
        if (dtoType.equalsIgnoreCase("STUDENT")){
            return new StudentModel();
        }

        if (dtoType.equalsIgnoreCase("Course")){
            return new CourseModel();
        }
        return null;
    }
}
