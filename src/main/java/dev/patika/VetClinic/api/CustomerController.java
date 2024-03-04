package dev.patika.VetClinic.api;


import dev.patika.VetClinic.dto.customer.CustomerSaveRequest;
import dev.patika.VetClinic.dto.customer.CustomerUpdateRequest;
import dev.patika.VetClinic.service.CustomerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static dev.patika.VetClinic.core.config.BaseURL.BASE_URL;

@RestController
@RequestMapping(BASE_URL + "customers")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    @GetMapping
    public ResponseEntity<?> getAll() {
        return new ResponseEntity<>(customerService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getResponseById(@PathVariable("id") Long id){
        return new ResponseEntity<>(customerService.getResponseById(id), HttpStatus.OK);
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<?> getResponseByName(@PathVariable("name") String name) {
        return new ResponseEntity<>(customerService.getResponseByName(name), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody CustomerSaveRequest customerSaveRequest) {
        return new ResponseEntity<>(customerService.create(customerSaveRequest), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<?> update(@Valid @RequestBody CustomerUpdateRequest customerUpdateRequest){
        return new ResponseEntity<>(customerService.update(customerUpdateRequest),HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        customerService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}