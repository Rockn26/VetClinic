package dev.patika.VetClinic.dto.request;

import dev.patika.VetClinic.entities.Doctor;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AvailableDateUpdateRequest {
    private Long id;
    private LocalDate availableDate;
    private long doctorId;
}
