package dev.patika.VetClinic.dto.doctor;

import dev.patika.VetClinic.dto.availabledate.OnlyAvailableDateResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DoctorResponse {

    private long id;
    private String name;
    private String phone;
    private String mail;
    private String address;
    private String city;
    private Set<OnlyAvailableDateResponse> availableDates;


}
