package dev.patika.VetClinic.dto.availabledate;

import dev.patika.VetClinic.dto.doctor.OnlyDoctorResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AvailableDateResponse {
    private Long id;
    private LocalDate availableDate;
    private OnlyDoctorResponse doctor;

}
