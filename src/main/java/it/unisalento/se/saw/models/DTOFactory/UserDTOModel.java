package it.unisalento.se.saw.models.DTOFactory;


import it.unisalento.se.saw.domain.User;
import it.unisalento.se.saw.dto.ProfessorDTO;
import it.unisalento.se.saw.dto.SecretaryDTO;
import it.unisalento.se.saw.dto.StudentDTO;
import it.unisalento.se.saw.dto.UserDTO;

public class UserDTOModel implements DTO<Object,UserDTO> {


    public UserDTO create(Object object) {
        if (object == null) {return new UserDTO();}
        if (object.getClass() == ProfessorDTO.class) {
            ProfessorDTO professorDTO = (ProfessorDTO) object;
            UserDTO userDTO = new UserDTO();
            userDTO.setAge(professorDTO.getAge());
            userDTO.setEmail(professorDTO.getEmail());
            userDTO.setName(professorDTO.getName());
            userDTO.setUid(professorDTO.getUid());
            userDTO.setSurname(professorDTO.getSurname());
            userDTO.setUserType(3);
            return userDTO;
        } else
        if (object.getClass() == StudentDTO.class) {
            StudentDTO studentDTO = (StudentDTO) object;
            UserDTO userDTO = new UserDTO();
            userDTO.setAge(studentDTO.getAge());
            userDTO.setEmail(studentDTO.getEmail());
            userDTO.setName(studentDTO.getName());
            userDTO.setUid(studentDTO.getUid());
            userDTO.setSurname(studentDTO.getSurname());
            userDTO.setUserType(1);
            return userDTO;

        } else
        if (object.getClass() == SecretaryDTO.class) {
            SecretaryDTO secretaryDTO = (SecretaryDTO) object;
            UserDTO userDTO = new UserDTO();
            userDTO.setAge(secretaryDTO.getAge());
            userDTO.setEmail(secretaryDTO.getEmail());
            userDTO.setName(secretaryDTO.getName());
            userDTO.setUid(secretaryDTO.getUid());
            userDTO.setSurname(secretaryDTO.getSurname());
            userDTO.setUserType(2);
            return userDTO;

        }else
            {

            User user = (User) object;
            UserDTO userDTO = new UserDTO();
            userDTO.setSurname(user.getSurname());
            userDTO.setName(user.getName());
            if (user.getIdUser() != null) {
                userDTO.setIdUser(user.getIdUser());
            }

            userDTO.setToken(user.getToken());
            if (user.getAge() != null) {
                userDTO.setAge(user.getAge());
            }

            userDTO.setEmail(user.getEmail());
            userDTO.setUid(user.getUid());
            if (user.getUserType() != null) {
                userDTO.setUserType(user.getUserType());
            }

            return userDTO;

        }

    }
}
