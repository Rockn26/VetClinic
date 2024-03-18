package dev.patika.VetClinic.dto.vaccine;

import dev.patika.VetClinic.dto.animal.OnlyAnimalResponse;
import dev.patika.VetClinic.dto.report.OnlyReportResponse;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VaccineResponse {
    private Long id;
    @NotBlank
    private String name;
    @NotBlank
    private String code;
    @NotEmpty
    private LocalDate protectionStartDate;
    @NotEmpty
    private LocalDate protectionFinishDate;

    private OnlyAnimalResponse animal;
    private OnlyReportResponse report;
}
