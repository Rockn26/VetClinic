package dev.patika.VetClinic.dao;

import dev.patika.VetClinic.entities.Animal;
import dev.patika.VetClinic.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface IAnimalRepo extends JpaRepository<Animal, Long> {

    Optional<Animal> findById(Long id);

    Optional<Animal> findByName(String name);

    List<Animal> findByCustomerId(Long customerId);


}
