package dev.patika.VetClinic.dto.response;

import dev.patika.VetClinic.entities.Animal;
import dev.patika.VetClinic.entities.Doctor;
import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AppointmentResponse {
    private Long id;
    private LocalDateTime appointmentDate;
    private long doctorId;
    private long animalId;
}
