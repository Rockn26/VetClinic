package dev.patika.VetClinic.dao;

import dev.patika.VetClinic.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ICustomerRepo extends JpaRepository<Customer, Long> {

    Optional<Customer> findByName(String name);


}