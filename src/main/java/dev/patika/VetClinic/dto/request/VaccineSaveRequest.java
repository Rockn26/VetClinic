package dev.patika.VetClinic.dto.request;

import dev.patika.VetClinic.entities.Animal;
import jakarta.persistence.Column;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
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
    @Temporal(TemporalType.DATE)
    private LocalDate protectionStartDate;
    @NotNull
    @Temporal(TemporalType.DATE)
    private LocalDate protectionFinishDate;

    private Animal animal;
}
