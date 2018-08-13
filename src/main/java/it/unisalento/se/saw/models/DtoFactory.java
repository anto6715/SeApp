package it.unisalento.se.saw.models;

public class DtoFactory extends AbstractFactory {
    @Override
    public DTO getDTO(String dtoType){
        if(dtoType == null){
            return null;
        }
        if (dtoType.equalsIgnoreCase("STUDENT")){
            return new StudentDTOModel();
        }

        if (dtoType.equalsIgnoreCase("SETLESSON")){
            return new SetLessonDTOModel();
        }

        if (dtoType.equalsIgnoreCase("SETSTUDENT")){
            return new SetStudentDTOModel();
        }

        if (dtoType.equalsIgnoreCase("MATERIAL")){
            return new MaterialDTOModel();
        }

        if (dtoType.equalsIgnoreCase("SETTEACHING")){
            return new SetTeachingDTOModel();
        }

        if (dtoType.equalsIgnoreCase("SETMATERIAL")){
            return new SetMaterialDTOModel();
        }

        if (dtoType.equalsIgnoreCase("Course")){
            return new CourseDTOModel();
        }

        if (dtoType.equalsIgnoreCase("Review")){
            return new ReviewDTOModel();
        }

        if (dtoType.equalsIgnoreCase("Lesson")){
            return new LessonDTOModel();
        }

        if (dtoType.equalsIgnoreCase("ROOM")){
            return new RoomDTOModel();
        }

        if (dtoType.equalsIgnoreCase("TEACHING")){
            return new TeachingDTOModel();
        }

        if (dtoType.equalsIgnoreCase("USER")){
            return new UserModel();
        }
        if (dtoType.equalsIgnoreCase("PROFESSOR")){
            return new ProfessorDTOModel();
        }
        if (dtoType.equalsIgnoreCase("SECRETARY")){
            return new SecretaryDTOModel();
        }
        return null;
    }

    @Override
    public Domain getDomain(String domainType) {
        return null;
    }
}
