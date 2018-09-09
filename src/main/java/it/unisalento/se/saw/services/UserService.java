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
import java.util.Set;

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

    AbstractFactory dtoFactory = FactoryProducer.getFactory("DTO");
    AbstractFactory abstractDomainFactory = FactoryProducer.getFactory("DOMAIN");

    @Transactional(readOnly=true)
    public Set<UserDTO> getAll(){
        DTO<List<User>, Set<UserDTO>> userDto = dtoFactory.getDTO("SETUSER");
        return userDto.create(userRepository.findAll());
    }

    @Transactional
    public UserDTO save(UserDTO userDTO) {
        DTO<User,UserDTO> userDto = dtoFactory.getDTO("User");
        Domain<UserDTO, User> domain = abstractDomainFactory.getDomain("User");
        return userDto.create(userRepository.save(domain.create(userDTO)));
    }


    @Transactional
    public UserDTO getById(int id) throws UserNotFoundException {
        try {
            DTO<User, UserDTO> userDto = dtoFactory.getDTO("User");
            return userDto.create(userRepository.getOne(id));
        } catch (Exception e) {
            throw new UserNotFoundException();
        }

    }

    @Transactional
    public Object getByUid(String uid) throws UserNotFoundException, StudentNotFoundException, SecretaryNotFoundException, ProfessorNotFoundException {
        User user = userRepository.findUserByUid(uid);
        if (user != null) {
            if(user.getUserType() ==1){
                try{
                    return studentServices.getByUid(uid);
                } catch (Exception e) {
                    throw new StudentNotFoundException();
                }
            } else
            if (user.getUserType() == 2) {
                try {
                    return secretaryServices.getByUid(uid);
                } catch (Exception e) {
                    throw new SecretaryNotFoundException();
                }
            }else  {
                try {
                    return professorServices.getByUid(uid);
                } catch (Exception e) {
                    throw new ProfessorNotFoundException();
                }
            }
        } else
            throw new UserNotFoundException();

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
