package it.unisalento.se.saw.Iservices;

import it.unisalento.se.saw.domain.User;
import it.unisalento.se.saw.dto.TokenDTO;
import it.unisalento.se.saw.exceptions.ProfessorNotFoundException;
import it.unisalento.se.saw.exceptions.SecretaryNotFoundException;
import it.unisalento.se.saw.exceptions.StudentNotFoundException;
import it.unisalento.se.saw.exceptions.UserNotFoundException;

import java.util.List;

public interface IUserServices {

    public List<User> getAll();
    public List<User> getByName(String name);
    public User save(User user);
    public User getById(int id) throws UserNotFoundException;
    public Object getByUid(String id) throws UserNotFoundException, StudentNotFoundException, SecretaryNotFoundException, ProfessorNotFoundException;
    public void removeUserById(int id) throws UserNotFoundException;
    public User updateName(int id, String name) throws UserNotFoundException;
    public User getByNameSurname(String name, String surname) throws UserNotFoundException;
    public TokenDTO addFcmToken(TokenDTO tokenDTO);
}
