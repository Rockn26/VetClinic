package dev.patika.VetClinic.service;

import dev.patika.VetClinic.core.config.ModelMapper.IModelMapperService;
import dev.patika.VetClinic.dao.IDoctorRepo;
import dev.patika.VetClinic.dto.doctor.DoctorResponse;
import dev.patika.VetClinic.dto.doctor.DoctorSaveRequest;
import dev.patika.VetClinic.dto.doctor.DoctorUpdateRequest;
import dev.patika.VetClinic.entities.Doctor;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DoctorService {

    private final IDoctorRepo doctorRepo;
    private final IModelMapperService modelMapper;

    public List<DoctorResponse> getAll() {
        List<Doctor> doctors = doctorRepo.findAll();

        return doctors.stream().map(doctor -> modelMapper
                        .forResponse()
                        .map(doctor, DoctorResponse.class))
                .collect(Collectors.toList());
    }

    public Doctor getById(Long id) {
        return doctorRepo.findById(id)
                .orElseThrow(()->new EntityNotFoundException("Entity with id " + id + " NOT FOUND"));
    }

    public DoctorResponse getResponseById(Long id) {
        return modelMapper
                .forResponse()
                .map(getById(id), DoctorResponse.class);
    }

    public DoctorResponse create(DoctorSaveRequest doctorSaveRequest) {
        Doctor saveDoctor = this.modelMapper
                .forRequest()
                .map(doctorSaveRequest, Doctor.class);

        return modelMapper
                .forResponse()
                .map(doctorRepo.save(saveDoctor), DoctorResponse.class);
    }

    public DoctorResponse update(DoctorUpdateRequest doctorUpdateRequest) {
        Doctor doesDoctorExist = getById(doctorUpdateRequest.getId());

        modelMapper
                .forRequest()
                .map(doctorUpdateRequest, doesDoctorExist);

        return modelMapper
                .forResponse()
                .map(doctorRepo.save(doesDoctorExist), DoctorResponse.class);
    }

    public void delete(Long id) {
        doctorRepo.delete(getById(id));
    }
}