package dev.patika.VetClinic.dao;

import dev.patika.VetClinic.entities.AvailableDate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface IAvailableDateRepo extends JpaRepository<AvailableDate, Long> {


}
