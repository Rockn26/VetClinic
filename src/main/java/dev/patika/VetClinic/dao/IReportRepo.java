package dev.patika.VetClinic.dao;

import dev.patika.VetClinic.entities.Report;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IReportRepo extends JpaRepository<Report, Long> {

}
