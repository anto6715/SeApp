package it.unisalento.se.saw.exceptions;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class ProfessorNotFoundException extends Exception {
    @Override
    public String getMessage() {
        return "Professor not found";
    }
}
