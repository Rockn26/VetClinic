package dev.patika.VetClinic.dto.doctor;

import dev.patika.VetClinic.dto.appointment.OnlyAppointmentResponse;
import dev.patika.VetClinic.dto.availabledate.OnlyAvailableDateResponse;
import dev.patika.VetClinic.dto.report.OnlyReportResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DoctorResponse {

    private long id;
    private String name;
    private String phone;
    private String email;
    private String address;
    private String city;
    private List<OnlyAvailableDateResponse> availableDates;
    private List<OnlyAppointmentResponse> appointments;


}
