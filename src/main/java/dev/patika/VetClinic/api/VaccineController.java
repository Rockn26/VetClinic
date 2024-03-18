package dev.patika.VetClinic.api;


import dev.patika.VetClinic.dto.vaccine.VaccineResponse;
import dev.patika.VetClinic.dto.vaccine.VaccineSaveRequest;
import dev.patika.VetClinic.dto.vaccine.VaccineUpdateRequest;
import dev.patika.VetClinic.service.VaccineService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

import static dev.patika.VetClinic.core.config.BaseURL.BASE_URL;

@RestController
@RequestMapping(BASE_URL + "vaccines")
@RequiredArgsConstructor
public class VaccineController {

    private final VaccineService vaccineService;

    @GetMapping
    public ResponseEntity<?> getAll() {
        return new ResponseEntity<>(vaccineService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getResponseById(@PathVariable("id") Long id){
        return new ResponseEntity<>(vaccineService.getResponseById(id), HttpStatus.OK);
    }

    @GetMapping("/by-animal-name/{name}")
    public ResponseEntity<List<VaccineResponse>> getVaccinesByAnimalName(@PathVariable String name) {
        return new ResponseEntity<>(vaccineService.getResponseByAnimalName(name), HttpStatus.OK);
    }

    @GetMapping("/by-date-range/{startDate}/{finishDate}")
    public ResponseEntity<List<VaccineResponse>> getVaccinesByDateRange(
            @PathVariable LocalDate startDate,
            @PathVariable LocalDate finishDate) {
        return new ResponseEntity<>(vaccineService.getVaccinesByDateRange(startDate, finishDate), HttpStatus.OK);
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