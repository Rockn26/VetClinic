package dev.patika.VetClinic.dao;

import dev.patika.VetClinic.entities.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IDoctorRepo extends JpaRepository<Doctor, Long> {

    Optional<Doctor> findById(Long id);

}
