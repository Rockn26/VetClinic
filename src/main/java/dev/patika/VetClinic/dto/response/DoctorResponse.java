package dev.patika.VetClinic.dto.response;

import dev.patika.VetClinic.entities.Appointment;
import dev.patika.VetClinic.entities.AvailableDate;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
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
    private String mail;
    private String address;
    private String city;


}
