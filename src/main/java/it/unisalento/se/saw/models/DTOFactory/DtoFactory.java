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

        if (dtoType.equalsIgnoreCase("ACCESSORY")){
            return new AccessoryDTOModel();
        }

        if (dtoType.equalsIgnoreCase("LISTACCESSORY")){
            return new ListAccessoryDTOModel();
        }

        if (dtoType.equalsIgnoreCase("EXAM")){
            return new ExamDTOModel();
        }

        if (dtoType.equalsIgnoreCase("REVIEWTYPE")){
            return new ReviewTypeDTOModel();
        }

        if (dtoType.equalsIgnoreCase("LISTREVIEWTYPE")){
            return new ListReviewTypeDTOModel();
        }

        if (dtoType.equalsIgnoreCase("LISTEXAM")){
            return new ListExamDTOModel();
        }

        if (dtoType.equalsIgnoreCase("SEGNALATIONSTATE")){
            return new SegnalationStateDTOModel();
        }

        if (dtoType.equalsIgnoreCase("LISTLESSON")){
            return new ListLessonDTOModel();
        }

        if (dtoType.equalsIgnoreCase("LISTSTUDENT")){
            return new ListStudentDTOModel();
        }

        if (dtoType.equalsIgnoreCase("LISTSEGNALATIONSTATE")){
            return new ListSegnalationStateDTOModel();
        }

        if (dtoType.equalsIgnoreCase("MATERIAL")){
            return new MaterialDTOModel();
        }

        if (dtoType.equalsIgnoreCase("SEGNALATION")){
            return new SegnalationDTOModel();
        }

        if (dtoType.equalsIgnoreCase("LISTSEGNALATION")){
            return new ListSegnalationDTOModel();
        }

        if (dtoType.equalsIgnoreCase("LISTTEACHING")){
            return new ListTeachingDTOModel();
        }

        if (dtoType.equalsIgnoreCase("LISTCOURSE")){
            return new ListCourseDTOModel();
        }

        if (dtoType.equalsIgnoreCase("LISTREVIEW")){
            return new ListReviewDTOModel();
        }

        if (dtoType.equalsIgnoreCase("LISTMATERIAL")){
            return new ListMaterialDTOModel();
        }

        if (dtoType.equalsIgnoreCase("LISTSECRETARY")){
            return new ListSecretaryDTOModel();
        }

        if (dtoType.equalsIgnoreCase("LISTROOM")){
            return new ListRoomDTOModel();
        }

        if (dtoType.equalsIgnoreCase("LISTPROFESSOR")){
            return new ListProfessorDTOModel();
        }

        if (dtoType.equalsIgnoreCase("LISTUSER")){
            return new ListUserDTOModel();
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
