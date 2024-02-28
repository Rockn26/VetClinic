package dev.patika.VetClinic.dto.vaccine;

import dev.patika.VetClinic.dto.animal.AnimalUpdateRequest;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VaccineSaveRequest {
    @NotNull
    private String name;
    @NotNull
    private String code;

    @NotNull
    private LocalDate protectionStartDate;
    @NotNull
    private LocalDate protectionFinishDate;

    private AnimalUpdateRequest animal;
}
