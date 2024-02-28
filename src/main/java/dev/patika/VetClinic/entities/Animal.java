package dev.patika.VetClinic.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Animal {
    @Id
    @GeneratedValue
    private Long id;

    private String name;
    private String species;
    private String breed;
    private String gender;
    private String colour;
    private LocalDate dateOfBirth;

    @ManyToOne(fetch = FetchType.EAGER)
    private Customer customer;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "animal", cascade = CascadeType.REMOVE)
    private Set<Vaccine> vaccines;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "animal", cascade = CascadeType.REMOVE)
    private Set<Appointment> appointments;




}
