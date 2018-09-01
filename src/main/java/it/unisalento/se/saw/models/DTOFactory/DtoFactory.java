package it.unisalento.se.saw.models.DTOFactory;

import it.unisalento.se.saw.models.AbstractFactory;
import it.unisalento.se.saw.models.DomainFactory.Domain;

public class DtoFactory extends AbstractFactory {
    @Override
    public DTO getDTO(String dtoType){
        if(dtoType == null){
            return null;
        }
        if (dtoType.equalsIgnoreCase("STUDENT")){
            return new StudentDTOModel();
        }

        if (dtoType.equalsIgnoreCase("SEGNALATIONSTATE")){
            return new SegnalationStateDTOModel();
        }

        if (dtoType.equalsIgnoreCase("SETLESSON")){
            return new SetLessonDTOModel();
        }

        if (dtoType.equalsIgnoreCase("SETSTUDENT")){
            return new SetStudentDTOModel();
        }

        if (dtoType.equalsIgnoreCase("SETSEGNALATIONSTATE")){
            return new SetSegnalationStateDTOModel();
        }

        if (dtoType.equalsIgnoreCase("MATERIAL")){
            return new MaterialDTOModel();
        }

        if (dtoType.equalsIgnoreCase("SEGNALATION")){
            return new SegnalationDTOModel();
        }

        if (dtoType.equalsIgnoreCase("SETSEGNALATION")){
            return new SetSegnalationDTOModel();
        }

        if (dtoType.equalsIgnoreCase("SETTEACHING")){
            return new SetTeachingDTOModel();
        }

        if (dtoType.equalsIgnoreCase("SETCOURSE")){
            return new SetCourseDTOModel();
        }

        if (dtoType.equalsIgnoreCase("SETREVIEW")){
            return new SetReviewDTOModel();
        }

        if (dtoType.equalsIgnoreCase("SETMATERIAL")){
            return new SetMaterialDTOModel();
        }

        if (dtoType.equalsIgnoreCase("SETSECRETARY")){
            return new SetSecretaryDTOModel();
        }

        if (dtoType.equalsIgnoreCase("SETROOM")){
            return new SetRoomDTOModel();
        }

        if (dtoType.equalsIgnoreCase("SETPROFESSOR")){
            return new SetProfessorDTOModel();
        }

        if (dtoType.equalsIgnoreCase("SETUSER")){
            return new SetUserDTOModel();
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
            return new UserDTOModel();
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
