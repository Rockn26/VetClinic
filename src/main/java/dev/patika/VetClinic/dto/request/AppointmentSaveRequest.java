package dev.patika.VetClinic.dto.request;

import dev.patika.VetClinic.entities.Animal;
import dev.patika.VetClinic.entities.Doctor;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AppointmentSaveRequest {
    @NotNull(message = "Randevu tarihi boş olamaz")
    private LocalDateTime appointmentDate;

    private DoctorUpdateRequest doctor;
    private AnimalUpdateRequest animal;

}
