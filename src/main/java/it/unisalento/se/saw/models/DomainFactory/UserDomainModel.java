package it.unisalento.se.saw.models.DomainFactory;

import it.unisalento.se.saw.domain.User;
import it.unisalento.se.saw.dto.ProfessorDTO;
import it.unisalento.se.saw.dto.SecretaryDTO;
import it.unisalento.se.saw.dto.StudentDTO;
import it.unisalento.se.saw.dto.UserDTO;

public class UserDomainModel implements Domain<Object, User> {
    @Override
    public User create(Object object) {
        if (object.getClass() == ProfessorDTO.class){
            ProfessorDTO professorDTO = (ProfessorDTO) object;
            User user = new User();
            user.setAge(professorDTO.getAge());
            user.setEmail(professorDTO.getEmail());
            user.setName(professorDTO.getName());
            user.setSurname(professorDTO.getSurname());
            user.setUid(professorDTO.getUid());
            user.setUserType(3);
            user.setIdUser(professorDTO.getIdUser());
            user.setToken(professorDTO.getToken());
            return user;
        }
        if (object.getClass() == StudentDTO.class){
            StudentDTO studentDTO = (StudentDTO) object;
            User user = new User();
            user.setAge(studentDTO.getAge());
            user.setEmail(studentDTO.getEmail());
            user.setName(studentDTO.getName());
            user.setSurname(studentDTO.getSurname());
            user.setUid(studentDTO.getUid());
            user.setUserType(1);
            user.setIdUser(studentDTO.getIdUser());
            user.setToken(studentDTO.getToken());
            return user;
        }
        if (object.getClass() == SecretaryDTO.class){
            SecretaryDTO secretaryDTO = (SecretaryDTO) object;
            User user = new User();
            user.setAge(secretaryDTO.getAge());
            user.setEmail(secretaryDTO.getEmail());
            user.setName(secretaryDTO.getName());
            user.setSurname(secretaryDTO.getSurname());
            user.setUid(secretaryDTO.getUid());
            user.setUserType(2);
            user.setIdUser(secretaryDTO.getIdUser());
            user.setToken(secretaryDTO.getToken());
            return user;
        }
        if (object.getClass() == UserDTO.class){
            UserDTO userDTO = (UserDTO) object;
            User user = new User();
            user.setName(userDTO.getName());
            user.setSurname(userDTO.getSurname());
            user.setAge(userDTO.getAge());
            user.setEmail(userDTO.getEmail());
            user.setUid(userDTO.getUid());
            user.setUserType(userDTO.getUserType());
            user.setToken(userDTO.getToken());
            user.setIdUser(userDTO.getIdUser());
            return user;
        }

        return null;
    }
}
