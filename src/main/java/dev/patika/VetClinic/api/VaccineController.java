package dev.patika.VetClinic.api;


import dev.patika.VetClinic.dto.vaccine.VaccineSaveRequest;
import dev.patika.VetClinic.dto.vaccine.VaccineUpdateRequest;
import dev.patika.VetClinic.service.VaccineService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/vaccines")
@RequiredArgsConstructor
public class VaccineController {

    @Autowired
    private final VaccineService vaccineService;

    @GetMapping
    public ResponseEntity<?> getAll() {
        return new ResponseEntity<>(vaccineService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getResponseById(@PathVariable("id") Long id){
        return new ResponseEntity<>(vaccineService.getResponseById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody VaccineSaveRequest vaccineSaveRequest) {
        return new ResponseEntity<>(vaccineService.create(vaccineSaveRequest), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<?> update(@Valid @RequestBody VaccineUpdateRequest vaccineUpdateRequest){
        return new ResponseEntity<>(vaccineService.update(vaccineUpdateRequest),HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        vaccineService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}