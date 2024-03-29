package dev.patika.VetClinic.api;


import dev.patika.VetClinic.dto.animal.AnimalSaveRequest;
import dev.patika.VetClinic.dto.animal.AnimalUpdateRequest;
import dev.patika.VetClinic.service.AnimalService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static dev.patika.VetClinic.core.config.BaseURL.BASE_URL;

@RestController
@RequestMapping(BASE_URL + "animals")
@RequiredArgsConstructor
public class AnimalController {

    private final AnimalService animalService;

    @GetMapping
    public ResponseEntity<?> getAll() {
        return new ResponseEntity<>(animalService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getResponseById(@PathVariable("id") Long id){
        return new ResponseEntity<>(animalService.getResponseById(id), HttpStatus.OK);
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<?> getResponseByName(@PathVariable("name") String name) {
        return new ResponseEntity<>(animalService.getResponseByName(name), HttpStatus.OK);
    }

    @GetMapping("/by-customer-name/{customerName}")
    public ResponseEntity<?> getResponseByCustomerName(@PathVariable("customerName") String customerName) {
        return new ResponseEntity<>(animalService.getResponseByCustomerName(customerName), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody AnimalSaveRequest animalSaveRequest) {
        return new ResponseEntity<>(animalService.create(animalSaveRequest), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<?> update(@Valid @RequestBody AnimalUpdateRequest animalUpdateRequest){
        return new ResponseEntity<>(animalService.update(animalUpdateRequest),HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        animalService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
