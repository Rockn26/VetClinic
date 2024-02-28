package dev.patika.VetClinic.dto.availabledate;

import dev.patika.VetClinic.dto.doctor.DoctorUpdateRequest;
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
    private DoctorUpdateRequest doctor;
}
