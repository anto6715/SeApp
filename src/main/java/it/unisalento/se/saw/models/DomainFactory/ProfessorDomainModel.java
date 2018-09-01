package it.unisalento.se.saw.models.DomainFactory;

import it.unisalento.se.saw.Iservices.ICourseServices;
import it.unisalento.se.saw.Iservices.IUserServices;
import it.unisalento.se.saw.domain.Course;
import it.unisalento.se.saw.domain.Professor;
import it.unisalento.se.saw.domain.ProfessorId;
import it.unisalento.se.saw.domain.User;
import it.unisalento.se.saw.dto.CourseDTO;
import it.unisalento.se.saw.dto.ProfessorDTO;
import it.unisalento.se.saw.dto.UserDTO;
import it.unisalento.se.saw.models.AbstractFactory;
import it.unisalento.se.saw.models.DTOFactory.DTO;
import it.unisalento.se.saw.models.FactoryProducer;
import it.unisalento.se.saw.repositories.ProfessorRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class ProfessorDomainModel implements Domain<ProfessorDTO, Professor> {
    @Autowired
    ProfessorRepository professorRepository;
    @Autowired
    IUserServices userServices;


    AbstractFactory domainFactory = FactoryProducer.getFactory("DOMAIN");
    AbstractFactory dtoFactory = FactoryProducer.getFactory("DTO");

    @Override
    public Professor create(ProfessorDTO professorDTO) {
        DTO<User, UserDTO> dto = dtoFactory.getDTO("User");
        Domain<ProfessorDTO,User> domainProfDTOUser = domainFactory.getDomain("USER");
        Domain<UserDTO,User> domainUserDTOUser = domainFactory.getDomain("USER");


        User user = domainProfDTOUser.create(professorDTO);


        User saveUser = domainUserDTOUser.create(userServices.save(dto.create(user)));

        ProfessorId professorId = new ProfessorId();
        professorId.setUserIdUser(user.getIdUser());

        Professor professor = new Professor();
        professor.setUser(user);
        professor.setId(professorId);

        return professor;
    }
}
