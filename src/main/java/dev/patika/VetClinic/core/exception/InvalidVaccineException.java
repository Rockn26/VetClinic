package dev.patika.VetClinic.core.exception;

public class InvalidVaccineException extends RuntimeException {

    public InvalidVaccineException(String message) {
        super(message);
    }
}
