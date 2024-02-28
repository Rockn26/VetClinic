package dev.patika.VetClinic.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Appointment {

    @Id
    @GeneratedValue
    private Long id;

    private LocalDateTime appointmentDate;

    @ManyToOne
    private Doctor doctor;


    @ManyToOne
    private Animal animal;




}
