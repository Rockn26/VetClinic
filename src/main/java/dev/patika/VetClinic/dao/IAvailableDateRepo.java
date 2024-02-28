package dev.patika.VetClinic.dao;

import dev.patika.VetClinic.entities.AvailableDate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;



@Repository
public interface IAvailableDateRepo extends JpaRepository<AvailableDate, Long> {


}
