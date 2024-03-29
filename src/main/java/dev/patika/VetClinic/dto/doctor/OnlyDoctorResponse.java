package dev.patika.VetClinic.dto.doctor;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class OnlyDoctorResponse {
    private long id;
    private String name;
    private String phone;
    private String email;
    private String address;
    private String city;
}
