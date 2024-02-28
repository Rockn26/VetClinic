package dev.patika.VetClinic.api;

import dev.patika.VetClinic.core.result.Result;
import dev.patika.VetClinic.core.result.ResultData;
import dev.patika.VetClinic.dto.request.AppointmentSaveRequest;
import dev.patika.VetClinic.dto.request.AppointmentUpdateRequest;
import dev.patika.VetClinic.dto.response.AppointmentResponse;
import dev.patika.VetClinic.service.AppointmentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/v1/appointments")
@RequiredArgsConstructor
public class AppointmentController {

    @Autowired
    private final AppointmentService appointmentService;

    @GetMapping("/all")
    @ResponseStatus(HttpStatus.OK)
    public ResultData<List<AppointmentResponse>> findAll() {
        return appointmentService.findAll();
    }


    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResultData<AppointmentResponse> findById(@PathVariable("id") Long id) {
        return appointmentService.findById(id);
    }

    @GetMapping("/by-doctor-and-date-range")
    @ResponseStatus(HttpStatus.OK)
    public ResultData<List<AppointmentResponse>> findAppointmentsByDoctorAndDateRange(
            @RequestParam Long doctorId,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startDate,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endDate) {

        return appointmentService.findAppointmentsByDoctorAndDateRange(doctorId, startDate, endDate);
    }

    @GetMapping("/by-animal-and-date-range")
    @ResponseStatus(HttpStatus.OK)
    public ResultData<List<AppointmentResponse>> findByAnimalAndDateRange(
            @RequestParam Long animalId,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startDate,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endDate) {
        return appointmentService.findByAnimalAndDateRange(animalId, startDate, endDate);
    }


    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public ResultData<AppointmentResponse> save(@Valid @RequestBody AppointmentSaveRequest appointmentSaveRequest) {
        return appointmentService.save(appointmentSaveRequest);
    }

    @PutMapping()
    @ResponseStatus(HttpStatus.OK)
    public ResultData<AppointmentResponse> update(@Valid @RequestBody AppointmentUpdateRequest appointmentUpdateRequest){
        return appointmentService.update(appointmentUpdateRequest);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Result delete(@PathVariable("id") Long id) {
        return appointmentService.delete(id);
    }

}
