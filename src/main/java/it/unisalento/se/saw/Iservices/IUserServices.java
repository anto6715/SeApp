package it.unisalento.se.saw.Iservices;

import it.unisalento.se.saw.dto.TokenDTO;
import it.unisalento.se.saw.dto.UserDTO;
import it.unisalento.se.saw.exceptions.ProfessorNotFoundException;
import it.unisalento.se.saw.exceptions.SecretaryNotFoundException;
import it.unisalento.se.saw.exceptions.StudentNotFoundException;
import it.unisalento.se.saw.exceptions.UserNotFoundException;

import java.util.Set;

public interface IUserServices {

    public Set<UserDTO> getAll();
    public UserDTO save(UserDTO userDTO);
    public UserDTO getById(int id) throws UserNotFoundException;
    public Object getByUid(String id) throws UserNotFoundException, StudentNotFoundException, SecretaryNotFoundException, ProfessorNotFoundException;
    public TokenDTO addFcmToken(TokenDTO tokenDTO);
}
