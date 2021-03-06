package it.unisalento.se.saw.exceptions;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class TeachingNotFoundException extends Exception {
    @Override
    public String getMessage() {
        return "Teaching not found";
    }
}
