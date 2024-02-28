package dev.patika.VetClinic.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class OnlyAppointmentResponse {
    private Long id;
    private LocalDateTime appointmentDate;
}
