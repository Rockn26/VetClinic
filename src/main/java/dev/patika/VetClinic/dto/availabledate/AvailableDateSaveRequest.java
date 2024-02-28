package dev.patika.VetClinic.dto.availabledate;

import dev.patika.VetClinic.dto.doctor.DoctorUpdateRequest;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AvailableDateSaveRequest {
    @NotNull(message = "Bu alan boş olamaz")
    private LocalDate availableDate;
    private DoctorUpdateRequest doctor;
}
