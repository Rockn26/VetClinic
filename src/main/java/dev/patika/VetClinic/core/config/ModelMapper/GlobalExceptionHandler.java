package dev.patika.VetClinic.core.config.ModelMapper;

import dev.patika.VetClinic.core.exception.*;
import dev.patika.VetClinic.core.result.ResultData;
import dev.patika.VetClinic.core.utilies.Msg;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleValidationErrors(MethodArgumentNotValidException e){
        List<String> validationErrorList = e.getBindingResult().getFieldErrors()
                .stream().map(FieldError::getDefaultMessage).collect(Collectors.toList());

        ResultData<List<String>> resultData = new ResultData<>(false, Msg.VALIDATE_ERROR,"400",validationErrorList);
        return new ResponseEntity<>(resultData, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(CustomerNotFoundException.class)
    public ResponseEntity<Object> handleCustomerNotFoundException(CustomerNotFoundException e) {
        ResultData<String> resultData = new ResultData<>(false, Msg.NOT_FOUND, "404", e.getMessage());
        return new ResponseEntity<>(resultData, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<Object> handleResponseStatusException(ResponseStatusException ex) {
        ResultData<String> resultData = new ResultData<>(false, Msg.VALIDATE_ERROR,"404", ex.getMessage());

        return new ResponseEntity<>(resultData, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(VetNotFoundException.class)
    public ResponseEntity<Object> handleVetNotFoundException(VetNotFoundException ex) {
        ResultData<String> resultData = new ResultData<>(false, Msg.NOT_FOUND_ANIMAL, "404", ex.getMessage());
        return new ResponseEntity<>(resultData, HttpStatus.NOT_FOUND);
    }


    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<Object> handleRuntimeException(RuntimeException ex) {

        String errorMessage = ex.getMessage();


        ResultData<String> resultData = new ResultData<>(false, Msg.ERR, "500", errorMessage);
        return new ResponseEntity<>(resultData, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(AnimalNotFoundException.class)
    public ResponseEntity<Object> handleAnimalNotFoundException(AnimalNotFoundException ex) {
        ResultData<String> resultData = new ResultData<>(false, Msg.ERR, "500", ex.getMessage());
        return new ResponseEntity<>(resultData, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(AvailableDateNotFoundException.class)
    public ResponseEntity<Object> handleAvailableDateNotFoundException(AvailableDateNotFoundException ex) {
        ResultData<String> resultData = new ResultData<>(false, Msg.NOT_FOUND, "404", ex.getMessage());
        return new ResponseEntity<>(resultData, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(DoctorNotFoundException.class)
    public ResponseEntity<Object> handleDoctorNotFoundException(DoctorNotFoundException ex) {
        ResultData<String> resultData = new ResultData<>(false, Msg.NOT_FOUND, "404", ex.getMessage());
        return new ResponseEntity<>(resultData, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(VaccineNotFoundException.class)
    public ResponseEntity<Object> handleVaccineNotFoundException(VaccineNotFoundException ex) {
        ResultData<String> resultData = new ResultData<>(false, Msg.NOT_FOUND, "404", ex.getMessage());
        return new ResponseEntity<>(resultData, HttpStatus.NOT_FOUND);
    }



}
