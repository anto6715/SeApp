package it.unisalento.se.saw.models;

import it.unisalento.se.saw.domain.Student;
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
            user.setUserType(professorDTO.getUserType());
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
            user.setUserType(studentDTO.getUserType());
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
            user.setUserType(secretaryDTO.getUserType());
            return user;
        }

        return null;
    }
}
