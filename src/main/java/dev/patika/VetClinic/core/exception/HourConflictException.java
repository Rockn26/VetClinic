package dev.patika.VetClinic.core.exception;

public class HourConflictException extends RuntimeException{
    public HourConflictException(String message) {
        super(message);
    }
}
