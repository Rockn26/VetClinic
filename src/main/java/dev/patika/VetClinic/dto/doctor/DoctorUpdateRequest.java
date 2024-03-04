package dev.patika.VetClinic.dto.doctor;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DoctorUpdateRequest {
    private Long id;
    private String name;
    private String phone;
    private String email;
    private String address;
    private String city;
}
