package dev.patika.VetClinic.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class OnlyAvailableDateResponse {
    private Long id;
    private LocalDate availableDate;
}
