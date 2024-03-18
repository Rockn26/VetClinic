package dev.patika.VetClinic.service;


import dev.patika.VetClinic.core.config.ModelMapper.IModelMapperService;
import dev.patika.VetClinic.core.exception.InvalidVaccineException;
import dev.patika.VetClinic.dao.IVaccineRepo;
import dev.patika.VetClinic.dto.customer.CustomerResponse;
import dev.patika.VetClinic.dto.vaccine.VaccineResponse;
import dev.patika.VetClinic.dto.vaccine.VaccineSaveRequest;
import dev.patika.VetClinic.dto.vaccine.VaccineUpdateRequest;
import dev.patika.VetClinic.entities.Customer;
import dev.patika.VetClinic.entities.Vaccine;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class VaccineService {

    private final IVaccineRepo vaccineRepo;
    private final IModelMapperService modelMapper;

    public List<VaccineResponse> getAll() {
        List<Vaccine> vaccines = vaccineRepo.findAll();

        return vaccines.stream().map(vaccine -> modelMapper
                        .forResponse()
                        .map(vaccine, VaccineResponse.class))
                .collect(Collectors.toList());
    }

    public Vaccine getById(Long id) {
        return vaccineRepo.findById(id)
                .orElseThrow(()->new EntityNotFoundException("Entity with id " + id + " NOT FOUND"));
    }

    public VaccineResponse getResponseById(Long id) {
        return modelMapper
                .forResponse()
                .map(getById(id), VaccineResponse.class);
    }


    public List<VaccineResponse> getVaccinesByDateRange(LocalDate startDate, LocalDate finishDate) {
        List<Vaccine> vaccines = vaccineRepo.findByProtectionFinishDateBetween(startDate, finishDate);
        return vaccines.stream()
                .map(vaccine -> modelMapper.forResponse().map(vaccine, VaccineResponse.class))
                .collect(Collectors.toList());
    }



    public List<Vaccine> getByAnimalName(String name) {
        return vaccineRepo.findByAnimalNameIgnoringCaseContaining(name);


    }
    public List<VaccineResponse> getResponseByAnimalName(String name) {
        return getByAnimalName(name)
                .stream().map(vaccine -> modelMapper
                        .forResponse()
                        .map(vaccine, VaccineResponse.class))
                .toList();
    }

    public VaccineResponse create(VaccineSaveRequest vaccineSaveRequest) {
        checkVaccine(vaccineSaveRequest);

        Vaccine saveVaccine = this.modelMapper
                .forRequest()
                .map(vaccineSaveRequest, Vaccine.class);

        return modelMapper
                .forResponse()
                .map(vaccineRepo.save(saveVaccine), VaccineResponse.class);
    }

    private void checkVaccine(VaccineSaveRequest vaccineSaveRequest) {
        Optional<Vaccine> vaccineMayBePresent = vaccineRepo.checkIfVaccineIsValid(
                vaccineSaveRequest.getName(),
                vaccineSaveRequest.getCode(),
                vaccineSaveRequest.getProtectionStartDate()
        );

        if (vaccineMayBePresent.isPresent())
            throw new InvalidVaccineException(vaccineSaveRequest.getProtectionStartDate() + " is not valid");
    }

    public VaccineResponse update(VaccineUpdateRequest vaccineUpdateRequest) {
        Vaccine doesVaccineExist = getById(vaccineUpdateRequest.getId());

        Vaccine vaccine = modelMapper
                .forResponse()
                .map(vaccineUpdateRequest, Vaccine.class);

        modelMapper
                .forRequest()
                .map(vaccine, doesVaccineExist);

        return modelMapper
                .forResponse()
                .map(vaccineRepo.save(doesVaccineExist), VaccineResponse.class);
    }

    public void delete(Long id) {
        vaccineRepo.delete(getById(id));
    }
}