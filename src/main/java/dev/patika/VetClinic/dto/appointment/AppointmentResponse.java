package dev.patika.VetClinic.dto.appointment;

import dev.patika.VetClinic.dto.animal.OnlyAnimalResponse;
import dev.patika.VetClinic.dto.doctor.OnlyDoctorResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AppointmentResponse {
    private Long id;
    private LocalDateTime appointmentDate;
    private OnlyDoctorResponse doctor;
    private OnlyAnimalResponse animal;
}
