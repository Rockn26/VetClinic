package dev.patika.VetClinic.dao;

import dev.patika.VetClinic.entities.Animal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IAnimalRepo extends JpaRepository<Animal, Long> {



    List<Animal> findByNameIgnoringCaseContaining(String name);


    @Query(value = "SELECT animal.* FROM animal INNER JOIN customer ON animal.customer_id = customer.id WHERE customer.name ILIKE %?1% ", nativeQuery = true)
    List<Animal> findByCustomerName(String customerName);


}
