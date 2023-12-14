package dev.patika.VetClinic.entities;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "animals")
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Animal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "animal_id", columnDefinition = "serial")
    private Long id;

    @Column(name = "animal_name", nullable = false)
    private String name;
    @Column(name = "animal_species")
    private String species;
    @Column(name = "animal_breed")
    private String breed;
    @Column(name = "animal_gender")
    private String gender;
    @Column(name = "animal_colour")
    private String colour;
    @Column(name = "animal_dateOfBirth")
    private LocalDate dateOfBirth;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "customer_id", referencedColumnName = "customer_id")
    private Customer customer;

    @JsonIgnore
    @OneToMany(mappedBy = "animal", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    private List<Vaccine> vaccineList;

    @JsonIgnore
    @OneToMany(mappedBy = "animal", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    private List<Appointment> appointmentList;




    @Override
    public String toString() {
        return "Animal{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", species='" + species + '\'' +
                ", breed='" + breed + '\'' +
                ", gender='" + gender + '\'' +
                ", colour='" + colour + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                '}';
    }
}
