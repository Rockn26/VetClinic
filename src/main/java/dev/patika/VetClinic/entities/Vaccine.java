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

@Entity
@Table(name = "vaccines")
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Vaccine {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "vaccine_id", columnDefinition = "serial")
    private long id;

    @Column(name = "vaccine_name", nullable = false)
    private String name;

    @Column(name = "vaccine_code", nullable = false)
    private String code;

    @Column(name = "vaccine_protectionStartDate" , nullable = false)
    @Temporal(TemporalType.DATE)
    private LocalDate protectionStartDate;

    @Column(name = "vaccine_protectionFinishDate" , nullable = false)
    @Temporal(TemporalType.DATE)
    private LocalDate protectionFinishDate;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "animal_id", referencedColumnName = "animal_id")
    @JsonIgnore
    private Animal animal;



    @Override
    public String toString() {
        return "Vaccine{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", code='" + code + '\'' +
                ", protectionStartDate=" + protectionStartDate +
                ", protectionFinishDate=" + protectionFinishDate +
                '}';
    }
}
