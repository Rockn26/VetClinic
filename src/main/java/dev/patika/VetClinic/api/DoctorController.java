package dev.patika.VetClinic.api;


import dev.patika.VetClinic.dto.doctor.DoctorSaveRequest;
import dev.patika.VetClinic.dto.doctor.DoctorUpdateRequest;
import dev.patika.VetClinic.service.DoctorService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/doctors")
@RequiredArgsConstructor
public class DoctorController {

    @Autowired
    private final DoctorService doctorService;

    @GetMapping
    public ResponseEntity<?> getAll() {
        return new ResponseEntity<>(doctorService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getResponseById(@PathVariable("id") Long id){
        return new ResponseEntity<>(doctorService.getResponseById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody DoctorSaveRequest doctorSaveRequest) {
        return new ResponseEntity<>(doctorService.create(doctorSaveRequest), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<?> update(@Valid @RequestBody DoctorUpdateRequest doctorUpdateRequest){
        return new ResponseEntity<>(doctorService.update(doctorUpdateRequest),HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        doctorService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}