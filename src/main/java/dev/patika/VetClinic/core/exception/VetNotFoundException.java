package dev.patika.VetClinic.core.exception;

public class VetNotFoundException extends RuntimeException{

    public VetNotFoundException(String message){
        super(message);
    }
}
