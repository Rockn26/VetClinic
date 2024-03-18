package dev.patika.VetClinic.api;


import dev.patika.VetClinic.core.config.BaseURL;
import dev.patika.VetClinic.dto.appointment.AppointmentSaveRequest;
import dev.patika.VetClinic.dto.appointment.AppointmentUpdateRequest;
import dev.patika.VetClinic.service.AppointmentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;

import static dev.patika.VetClinic.core.config.BaseURL.BASE_URL;

@RestController
@RequestMapping(BASE_URL + "appointments")
@RequiredArgsConstructor
public class AppointmentController {

    private final AppointmentService appointmentService;

    @GetMapping
    public ResponseEntity<?> getAll() {
        return new ResponseEntity<>(appointmentService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getResponseById(@PathVariable("id") Long id){
        return new ResponseEntity<>(appointmentService.getResponseById(id), HttpStatus.OK);
    }

    @GetMapping("/doctor-and-dates")
    public ResponseEntity<?> filterByDoctorNameAndDateBetween(

            @RequestParam(value = "doctorName", required = false, defaultValue = "")  String doctorName,
            @RequestParam(value = "startDate", required = false, defaultValue = "1970-01-01") LocalDate startDate,
            @RequestParam(value = "finishDate", required = false, defaultValue = "2050-01-01") LocalDate finishDate
            ){
        return new ResponseEntity<>(appointmentService
                .filterByDoctorNameAndDateBetween(doctorName, startDate, finishDate), HttpStatus.OK);
    }

    @GetMapping("/animal-and-dates")
    public ResponseEntity<?> filterByAnimalNameAndDateBetween(
            @RequestParam(value = "animalName", required = false, defaultValue = "") String animalName,
            @RequestParam(value = "startDate", required = false, defaultValue = "1970-01-01") LocalDate startDate,
            @RequestParam(value = "finishDate", required = false, defaultValue = "2050-01-01") LocalDate finishDate
    ){
        return new ResponseEntity<>(appointmentService
                .filterByAnimalNameAndDateBetween(animalName, startDate, finishDate), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody AppointmentSaveRequest appointmentSaveRequest) {
        return new ResponseEntity<>(appointmentService.create(appointmentSaveRequest), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<?> update(@Valid @RequestBody AppointmentUpdateRequest appointmentUpdateRequest){
        return new ResponseEntity<>(appointmentService.update(appointmentUpdateRequest),HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        appointmentService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}