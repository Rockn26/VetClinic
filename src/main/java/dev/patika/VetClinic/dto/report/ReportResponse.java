package dev.patika.VetClinic.dto.report;


import dev.patika.VetClinic.dto.appointment.OnlyAppointmentResponse;
import dev.patika.VetClinic.dto.vaccine.OnlyVaccineResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReportResponse {
    private Long id;

    private String title;
    private String diagnosis;
    private double price;
    private OnlyAppointmentResponse appointment;
    private List<OnlyVaccineResponse> vaccines;
}
