package dev.patika.VetClinic.api;


import dev.patika.VetClinic.core.result.Result;
import dev.patika.VetClinic.core.result.ResultData;
import dev.patika.VetClinic.dto.request.AnimalSaveRequest;
import dev.patika.VetClinic.dto.request.AnimalUpdateRequest;
import dev.patika.VetClinic.dto.request.VaccineSaveRequest;
import dev.patika.VetClinic.dto.request.VaccineUpdateRequest;
import dev.patika.VetClinic.dto.response.AnimalResponse;
import dev.patika.VetClinic.dto.response.CustomerResponse;
import dev.patika.VetClinic.dto.response.VaccineResponse;
import dev.patika.VetClinic.entities.Animal;
import dev.patika.VetClinic.service.AnimalService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/v1/animals")
@RequiredArgsConstructor
public class AnimalController {

    @Autowired
    private final AnimalService animalService;

    @GetMapping("/all")
    @ResponseStatus(HttpStatus.OK)
    public ResultData<List<AnimalResponse>> findAll() {
        return animalService.findAll();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResultData<AnimalResponse> findById(@PathVariable("id") Long id){
        return animalService.findById(id);
    }



    @GetMapping("/name/{name}")
    @ResponseStatus(HttpStatus.OK)
    public ResultData<AnimalResponse> findByName(@PathVariable("name") String name) {
        return this.animalService.findByName(name);
    }

    @GetMapping("/by-customer/{customerId}")
    @ResponseStatus(HttpStatus.OK)
    public ResultData<List<AnimalResponse>> findByCustomerId(@PathVariable("customerId") Long customerId) {
        return animalService.findByCustomerId(customerId);
    }




    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public ResultData<AnimalResponse> save(@Valid @RequestBody AnimalSaveRequest animalSaveRequest) {
        return animalService.save(animalSaveRequest);
    }

    @PutMapping()
    @ResponseStatus(HttpStatus.OK)
    public ResultData<AnimalResponse> update(@Valid @RequestBody AnimalUpdateRequest animalUpdateRequest){
        return animalService.update(animalUpdateRequest);
    }


    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Result delete(@PathVariable("id") Long id) {
        return animalService.delete(id);
    }





}
