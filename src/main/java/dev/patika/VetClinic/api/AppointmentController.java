package dev.patika.VetClinic.api;


import dev.patika.VetClinic.dto.appointment.AppointmentSaveRequest;
import dev.patika.VetClinic.dto.appointment.AppointmentUpdateRequest;
import dev.patika.VetClinic.service.AppointmentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/appointments")
@RequiredArgsConstructor
public class AppointmentController {

    @Autowired
    private final AppointmentService appointmentService;

    @GetMapping
    public ResponseEntity<?> getAll() {
        return new ResponseEntity<>(appointmentService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getResponseById(@PathVariable("id") Long id){
        return new ResponseEntity<>(appointmentService.getResponseById(id), HttpStatus.OK);
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