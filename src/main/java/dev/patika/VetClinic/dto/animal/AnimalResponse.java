package dev.patika.VetClinic.dto.animal;

import dev.patika.VetClinic.dto.customer.OnlyCustomerResponse;
import dev.patika.VetClinic.dto.report.OnlyReportResponse;
import dev.patika.VetClinic.dto.vaccine.OnlyVaccineResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AnimalResponse {
    private Long id;
    private String name;
    private String species;
    private String breed;
    private String gender;
    private String colour;
    private LocalDate dateOfBirth;
    private OnlyCustomerResponse customer;
    private List<OnlyVaccineResponse> vaccines;


}
