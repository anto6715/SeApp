package it.unisalento.se.saw.services;


import it.unisalento.se.saw.Iservices.IProfessorServices;
import it.unisalento.se.saw.Iservices.ISecretaryServices;
import it.unisalento.se.saw.Iservices.IStudentServices;
import it.unisalento.se.saw.Iservices.IUserServices;
import it.unisalento.se.saw.domain.Professor;
import it.unisalento.se.saw.domain.Secretary;
import it.unisalento.se.saw.domain.Student;
import it.unisalento.se.saw.domain.User;
import it.unisalento.se.saw.dto.*;
import it.unisalento.se.saw.exceptions.ProfessorNotFoundException;
import it.unisalento.se.saw.exceptions.SecretaryNotFoundException;
import it.unisalento.se.saw.exceptions.StudentNotFoundException;
import it.unisalento.se.saw.exceptions.UserNotFoundException;
import it.unisalento.se.saw.models.AbstractFactory;
import it.unisalento.se.saw.models.DTOFactory.DTO;
import it.unisalento.se.saw.models.DomainFactory.Domain;
import it.unisalento.se.saw.models.FactoryProducer;
import it.unisalento.se.saw.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserService implements IUserServices {

    @Autowired
    UserRepository userRepository;

    @Autowired
    IStudentServices studentServices;

    @Autowired
    IProfessorServices professorServices;

    @Autowired
    ISecretaryServices secretaryServices;

    AbstractFactory abstractDTOFactory = FactoryProducer.getFactory("DTO");
    AbstractFactory abstractDomainFactory = FactoryProducer.getFactory("DTO");

    @Transactional(readOnly=true)
    public List<User> getAll(){
        return userRepository.findAll();
    }

    @Transactional
    public UserDTO save(UserDTO userDTO) {
        DTO<User,UserDTO> dto = abstractDTOFactory.getDTO("User");
        Domain<UserDTO, User> domain = abstractDomainFactory.getDomain("User");
        return dto.create(userRepository.save(domain.create(userDTO)));
    }


    @Transactional
    public UserDTO getById(int id) throws UserNotFoundException {
        try {
            DTO<User, UserDTO> dto = abstractDTOFactory.getDTO("User");
            return dto.create(userRepository.getOne(id));
        } catch (Exception e) {
            throw new UserNotFoundException();
        }

    }

    @Transactional
    public Object getByUid(String uid) throws UserNotFoundException, StudentNotFoundException, SecretaryNotFoundException, ProfessorNotFoundException {
        User user = userRepository.findUserByUid(uid);
        AbstractFactory abstractFactory = FactoryProducer.getFactory("DTO");
        if(user.getUserType() ==1){
            try{
                DTO<Student, StudentDTO> dto = abstractFactory.getDTO("Student");
                return dto.create(studentServices.getByUid(uid));
            } catch (Exception e) {
                throw new StudentNotFoundException();
            }
        } else
        if (user.getUserType() == 2) {
            try {
                DTO<Secretary, SecretaryDTO> dto = abstractFactory.getDTO("Secretary");
                return dto.create(secretaryServices.getByUid(uid));
            } catch (Exception e) {
                throw new SecretaryNotFoundException();
            }
        }else  {
            try {
                DTO<Professor, ProfessorDTO> dto = abstractFactory.getDTO("Professor");
                return dto.create(professorServices.getByUid(uid));
            } catch (Exception e) {
                throw new ProfessorNotFoundException();
            }
        }


    }

    @Transactional
    public TokenDTO addFcmToken(TokenDTO tokenDTO){
        User user = userRepository.getOne(tokenDTO.getIdUser());
        user.setToken(tokenDTO.getToken());
        User tkn = userRepository.save(user);
        TokenDTO token = new TokenDTO();
        token.setIdUser(tkn.getIdUser());
        token.setToken(tkn.getToken());
        return token;
    }



}
