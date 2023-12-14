package dev.patika.VetClinic.core.exception;

public class AnimalNotFoundException extends RuntimeException{
    public AnimalNotFoundException(String message) {
        super(message);
    }
}
