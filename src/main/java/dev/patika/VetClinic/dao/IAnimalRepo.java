package dev.patika.VetClinic.dao;

import dev.patika.VetClinic.entities.Animal;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface IAnimalRepo extends JpaRepository<Animal, Long> {

    Optional<Animal> findById(Long id);

    Optional<Animal> findByName(String name);

    List<Animal> findByCustomerId(Long customerId);


}
