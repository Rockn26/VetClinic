package dev.patika.VetClinic.dao;

import dev.patika.VetClinic.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepo extends JpaRepository<Customer, Long> {

    Optional<Customer> findById(Long id);
    Optional<Customer> findByName(String name);


}
