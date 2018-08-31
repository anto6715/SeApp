package it.unisalento.se.saw.services;


import it.unisalento.se.saw.Iservices.IProfessorServices;
import it.unisalento.se.saw.Iservices.ISecretaryServices;
import it.unisalento.se.saw.Iservices.IStudentServices;
import it.unisalento.se.saw.Iservices.IUserServices;
import it.unisalento.se.saw.domain.Professor;
import it.unisalento.se.saw.domain.Secretary;
import it.unisalento.se.saw.domain.Student;
import it.unisalento.se.saw.domain.User;
import it.unisalento.se.saw.dto.ProfessorDTO;
import it.unisalento.se.saw.dto.SecretaryDTO;
import it.unisalento.se.saw.dto.StudentDTO;
import it.unisalento.se.saw.dto.TokenDTO;
import it.unisalento.se.saw.exceptions.ProfessorNotFoundException;
import it.unisalento.se.saw.exceptions.SecretaryNotFoundException;
import it.unisalento.se.saw.exceptions.StudentNotFoundException;
import it.unisalento.se.saw.exceptions.UserNotFoundException;
import it.unisalento.se.saw.models.AbstractFactory;
import it.unisalento.se.saw.models.DTOFactory.DTO;
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

    @Transactional(readOnly=true)
    public List<User> getAll(){
        return userRepository.findAll();
    }

    @Transactional
    public User save(User user) {
        return userRepository.save(user);
    }

    @Transactional
    public List<User> getByName(String name){
        return userRepository.findUsersByName(name);
    }

    @Transactional
    public User getById(int id) throws UserNotFoundException {
        try {
            User user = userRepository.getOne(id);
            return user;
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


    @Transactional(rollbackFor = UserNotFoundException.class)
    public void removeUserById(int id) throws UserNotFoundException {
        try {
            User user = userRepository.getOne(id);
            userRepository.delete(user);
        } catch (Exception e){
            throw new UserNotFoundException();
        }
    }

    @Transactional
    public User updateName(int id, String name) throws UserNotFoundException {
        try {
            User user = userRepository.getOne(id);
            user.setName(name);
            userRepository.save(user);
            return user;
        }catch (Exception e) {
            throw new UserNotFoundException();
        }
    }
    @Transactional
    public User getByNameSurname(String name, String surname) throws UserNotFoundException {
        try {
            return userRepository.getUserByNameAndSurname(name, surname);
        } catch (Exception e) {
            throw new UserNotFoundException();
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
