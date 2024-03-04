package dev.patika.VetClinic.dto.vaccine;

import dev.patika.VetClinic.dto.animal.AnimalUpdateRequest;
import dev.patika.VetClinic.dto.report.ReportUpdateRequest;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class VaccineUpdateRequest {
    @Positive(message = "Alan pozitif olmalı")
    private long id;
    private String name;
    private String code;
    private LocalDate protectionStartDate;
    private LocalDate protectionFinishDate;

    private AnimalUpdateRequest animal;
    private ReportUpdateRequest report;
}
