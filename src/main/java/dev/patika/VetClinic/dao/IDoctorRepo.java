package dev.patika.VetClinic.dao;

import dev.patika.VetClinic.entities.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IDoctorRepo extends JpaRepository<Doctor, Long> {


}
