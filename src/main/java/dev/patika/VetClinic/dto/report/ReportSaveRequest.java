package dev.patika.VetClinic.dto.report;


import dev.patika.VetClinic.dto.appointment.AppointmentUpdateRequest;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReportSaveRequest {
    private String title;
    private String diagnosis;
    private double price;
    private AppointmentUpdateRequest appointment;
}
