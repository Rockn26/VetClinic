package dev.patika.VetClinic.dto.request;

import dev.patika.VetClinic.entities.Animal;
import dev.patika.VetClinic.entities.Doctor;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.print.Doc;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AppointmentUpdateRequest {
    private Long id;
    private LocalDateTime appointmentDate;
    private long doctorId;
    private long animalId;

}

