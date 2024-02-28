package dev.patika.VetClinic.dao;

import dev.patika.VetClinic.entities.Animal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IAnimalRepo extends JpaRepository<Animal, Long> {


    Optional<Animal> findByName(String name);

    List<Animal> findByCustomerId(Long customerId);


}
