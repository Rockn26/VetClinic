package dev.patika.VetClinic.dto.report;


import dev.patika.VetClinic.dto.appointment.AppointmentUpdateRequest;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReportSaveRequest {
    @NotBlank
    private String title;
    @NotBlank
    private String diagnosis;
    @PositiveOrZero
    private double price;
    private AppointmentUpdateRequest appointment;
}
