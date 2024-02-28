package dev.patika.VetClinic.api;


import dev.patika.VetClinic.dto.availabledate.AvailableDateSaveRequest;
import dev.patika.VetClinic.dto.availabledate.AvailableDateUpdateRequest;
import dev.patika.VetClinic.service.AvailableDateService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/availableDates")
@RequiredArgsConstructor
public class AvailableDateController {

    private final AvailableDateService availableDateService;

    @GetMapping
    public ResponseEntity<?> getAll() {
        return new ResponseEntity<>(availableDateService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getResponseById(@PathVariable("id") Long id){
        return new ResponseEntity<>(availableDateService.getResponseById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody AvailableDateSaveRequest availableDateSaveRequest) {
        return new ResponseEntity<>(availableDateService.create(availableDateSaveRequest), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<?> update(@Valid @RequestBody AvailableDateUpdateRequest availableDateUpdateRequest){
        return new ResponseEntity<>(availableDateService.update(availableDateUpdateRequest),HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        availableDateService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}