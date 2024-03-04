package dev.patika.VetClinic.service;

import dev.patika.VetClinic.core.config.ModelMapper.IModelMapperService;
import dev.patika.VetClinic.dao.IAvailableDateRepo;
import dev.patika.VetClinic.dto.availabledate.AvailableDateResponse;
import dev.patika.VetClinic.dto.availabledate.AvailableDateSaveRequest;
import dev.patika.VetClinic.dto.availabledate.AvailableDateUpdateRequest;
import dev.patika.VetClinic.entities.AvailableDate;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AvailableDateService {

    private final IAvailableDateRepo availableDateRepo;
    private final IModelMapperService modelMapper;

    public List<AvailableDateResponse> getAll() {
        List<AvailableDate> availableDates = availableDateRepo.findAll();

        return availableDates.stream().map(availableDate -> modelMapper
                        .forResponse()
                        .map(availableDate, AvailableDateResponse.class))
                .collect(Collectors.toList());
    }

    public AvailableDate getById(Long id) {
        return availableDateRepo.findById(id)
                .orElseThrow(()->new EntityNotFoundException("Entity with id " + id + " NOT FOUND"));
    }

    public AvailableDateResponse getResponseById(Long id) {
        return modelMapper
                .forResponse()
                .map(getById(id), AvailableDateResponse.class);
    }

    public AvailableDateResponse create(AvailableDateSaveRequest availableDateSaveRequest) {
        AvailableDate saveAvailableDate = this.modelMapper
                .forRequest()
                .map(availableDateSaveRequest, AvailableDate.class);

        return modelMapper
                .forResponse()
                .map(availableDateRepo.save(saveAvailableDate), AvailableDateResponse.class);
    }

    public AvailableDateResponse update(AvailableDateUpdateRequest availableDateUpdateRequest) {
        AvailableDate doesAvailableDateExist = getById(availableDateUpdateRequest.getId());

        modelMapper
                .forRequest()
                .map(availableDateUpdateRequest, doesAvailableDateExist);

        return modelMapper
                .forResponse()
                .map(availableDateRepo.save(doesAvailableDateExist), AvailableDateResponse.class);
    }

    public void delete(Long id) {
        availableDateRepo.delete(getById(id));
    }

    public Optional<AvailableDate> getByAvailableDate(LocalDate availableDate) {
        return availableDateRepo.findByAvailableDate(availableDate);
    }

}