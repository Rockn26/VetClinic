package dev.patika.VetClinic.dto.doctor;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DoctorSaveRequest {
    @NotBlank
    private String name;
    @NotBlank
    private String phone;
    @NotBlank
    private String email;
    @NotBlank
    private String address;
    @NotBlank
    private String city;

}
