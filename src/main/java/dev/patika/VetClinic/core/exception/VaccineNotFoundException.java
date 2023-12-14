package dev.patika.VetClinic.core.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class VaccineNotFoundException extends RuntimeException{

    public VaccineNotFoundException(String message) {
        super(message);
    }
}
