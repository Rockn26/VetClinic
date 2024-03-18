package dev.patika.VetClinic.dto.animal;

import dev.patika.VetClinic.dto.customer.CustomerUpdateRequest;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AnimalUpdateRequest {

    private Long id;
    @NotBlank
    private String name;
    @NotBlank
    private String species;
    @NotBlank
    private String breed;
    @NotBlank
    private String gender;
    @NotBlank
    private String colour;
    @NotNull
    private LocalDate dateOfBirth;
    private CustomerUpdateRequest customer;
}
