package dev.patika.VetClinic.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AvailableDate {

    @Id
    @GeneratedValue
    private Long id;

    private LocalDate availableDate;

    @ManyToOne
    private Doctor doctor;

}

