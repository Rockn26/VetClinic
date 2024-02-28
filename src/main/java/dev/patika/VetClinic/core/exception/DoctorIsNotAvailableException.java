package dev.patika.VetClinic.core.exception;

public class DoctorIsNotAvailableException extends RuntimeException {
    public DoctorIsNotAvailableException(String message) {
        super(message);
    }
}
