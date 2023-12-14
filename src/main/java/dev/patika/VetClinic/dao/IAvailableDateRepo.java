package dev.patika.VetClinic.dao;

import dev.patika.VetClinic.entities.AvailableDate;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IAvailableDateRepo extends JpaRepository<AvailableDate, Long> {

    Optional<AvailableDate> findById(Long id);

}
