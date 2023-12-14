package dev.patika.VetClinic.api;

import dev.patika.VetClinic.core.result.Result;
import dev.patika.VetClinic.core.result.ResultData;
import dev.patika.VetClinic.dto.request.VaccineSaveRequest;
import dev.patika.VetClinic.dto.request.VaccineUpdateRequest;
import dev.patika.VetClinic.dto.response.AnimalResponse;
import dev.patika.VetClinic.dto.response.VaccineResponse;
import dev.patika.VetClinic.entities.Vaccine;
import dev.patika.VetClinic.service.VaccineService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/v1/vaccines")
@RequiredArgsConstructor
public class VaccineController {

    private final VaccineService vaccineService;

    @GetMapping("/all")
    @ResponseStatus(HttpStatus.OK)
    public ResultData<List<VaccineResponse>> findAll() {
        return vaccineService.findAll();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResultData<VaccineResponse> findById(@PathVariable("id") Long id){
        return vaccineService.findById(id);
    }

    @GetMapping("/animal/{animalId}")
    @ResponseStatus(HttpStatus.OK)
    public ResultData<List<VaccineResponse>> findAllByAnimalId(@PathVariable Long animalId) {

        return vaccineService.findAllByAnimalId(animalId);
    }

    @GetMapping("/by-protection-date-range")
    @ResponseStatus(HttpStatus.OK)
    public ResultData<List<VaccineResponse>> findVaccinesByProtectionDateRange(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
        return vaccineService.findVaccinesByProtectionDateRange(startDate, endDate);
    }



    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public ResultData<VaccineResponse> save(@Valid @RequestBody VaccineSaveRequest vaccineSaveRequest) {
        return vaccineService.save(vaccineSaveRequest);
    }

    @PutMapping()
    @ResponseStatus(HttpStatus.OK)
    public ResultData<VaccineResponse> update(@Valid @RequestBody VaccineUpdateRequest vaccineUpdateRequest){
        return vaccineService.update(vaccineUpdateRequest);
    }


    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Result delete(@PathVariable("id") Long id) {
        return vaccineService.delete(id);
    }




}
