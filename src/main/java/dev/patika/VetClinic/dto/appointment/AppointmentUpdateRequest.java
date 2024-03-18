package dev.patika.VetClinic.dto.appointment;

import dev.patika.VetClinic.dto.animal.AnimalUpdateRequest;
import dev.patika.VetClinic.dto.doctor.DoctorUpdateRequest;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AppointmentUpdateRequest {
    private Long id;
    @NotNull
    private LocalDateTime appointmentDate;
    private DoctorUpdateRequest doctor;
    private AnimalUpdateRequest animal;

}

