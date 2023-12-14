package dev.patika.VetClinic.dto.request;

import dev.patika.VetClinic.entities.Doctor;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.print.Doc;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AvailableDateSaveRequest {
    @NotNull(message = "Bu alan boş olamaz")
    private LocalDate availableDate;
    private long doctorId;
}
