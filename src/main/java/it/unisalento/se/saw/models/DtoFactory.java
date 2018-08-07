package it.unisalento.se.saw.models;

public class DtoFactory {

    public DTO getDTO(String dtoType){
        if(dtoType == null){
            return null;
        }
        if (dtoType.equalsIgnoreCase("STUDENT")){
            return new StudentDTOModel();
        }

        if (dtoType.equalsIgnoreCase("Course")){
            return new CourseModel();
        }

        if (dtoType.equalsIgnoreCase("USER")){
            return new UserModel();
        }
        if (dtoType.equalsIgnoreCase("PROFESSOR")){
            return new ProfessorDTOModel();
        }
        return null;
    }
}
