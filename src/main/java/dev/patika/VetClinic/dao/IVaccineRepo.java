package dev.patika.VetClinic.dao;

import dev.patika.VetClinic.entities.Vaccine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface IVaccineRepo extends JpaRepository<Vaccine, Long> {


    Optional<Vaccine> findByCodeAndNameAndProtectionFinishDateAfter(String code, String name, LocalDate date);

    // Aynı tip aşıların ve bitiş tarihinin kontrolünü yapacak özel sorgu
    @Query("SELECT v FROM Vaccine v WHERE v.name = :name AND v.code = :code AND v.protectionFinishDate >= :protectionFinishDate")
    List<Vaccine> findExistingVaccines(@Param("name") String name, @Param("code") String code, @Param("protectionFinishDate") LocalDate protectionFinishDate);


    List<Vaccine> findByAnimalId(Long animalId);

    List<Vaccine> findByProtectionFinishDateBetween(LocalDate startDate, LocalDate endDate);




}
