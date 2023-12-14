package dev.patika.VetClinic.dto.request;

import dev.patika.VetClinic.entities.Animal;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.NotNull;
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
    @Temporal(TemporalType.DATE)
    private LocalDate protectionStartDate;
    @Temporal(TemporalType.DATE)
    private LocalDate protectionFinishDate;

    private long animalId;
}
