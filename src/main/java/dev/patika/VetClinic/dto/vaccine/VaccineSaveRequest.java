package dev.patika.VetClinic.dto.vaccine;

import dev.patika.VetClinic.dto.animal.AnimalUpdateRequest;
import dev.patika.VetClinic.dto.report.ReportUpdateRequest;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VaccineSaveRequest {
    @NotBlank
    private String name;
    @NotBlank
    private String code;
    @NotNull
    private LocalDate protectionStartDate;
    @NotNull
    private LocalDate protectionFinishDate;

    private AnimalUpdateRequest animal;
    private ReportUpdateRequest report;
}
