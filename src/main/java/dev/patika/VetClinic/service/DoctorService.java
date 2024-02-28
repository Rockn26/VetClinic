package dev.patika.VetClinic.service;

import dev.patika.VetClinic.core.config.ModelMapper.IModelMapperService;
import dev.patika.VetClinic.core.exception.DoctorNotFoundException;
import dev.patika.VetClinic.core.result.Result;
import dev.patika.VetClinic.core.result.ResultData;
import dev.patika.VetClinic.core.utilies.ResultHelper;
import dev.patika.VetClinic.dao.IDoctorRepo;
import dev.patika.VetClinic.dto.doctor.DoctorSaveRequest;
import dev.patika.VetClinic.dto.doctor.DoctorUpdateRequest;
import dev.patika.VetClinic.dto.doctor.DoctorResponse;
import dev.patika.VetClinic.entities.Doctor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DoctorService {

    @Autowired
    private final IDoctorRepo doctorRepo;
    private final IModelMapperService modelMapper;


    //25- Exception kullanılmış mı? (id ile güncelleme, silme işlemlerinde girilen id’de veri yoksa hata fırlatma gibi)
    public ResultData<DoctorResponse> findByID(Long id) {
        Optional<Doctor> optionalDoctor = Optional.ofNullable((this.doctorRepo.findById(id).orElseThrow(() -> new DoctorNotFoundException("Böyle bir id numaralı doktor yok "))));
        if (optionalDoctor.isPresent()) {
            Doctor doctor = optionalDoctor.get();
            DoctorResponse doctorResponse = this.modelMapper.forResponse().map(doctor, DoctorResponse.class);
            return ResultHelper.success(doctorResponse);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Doctor not found");
        }
    }

    public ResultData<List<DoctorResponse>> findAll() {
        List<Doctor> doctors = this.doctorRepo.findAll();
        List<DoctorResponse> doctorResponses = doctors.stream()
                .map(doctor -> this.modelMapper.forResponse().map(doctor, DoctorResponse.class))
                .collect(Collectors.toList());
        return ResultHelper.success(doctorResponses);
    }


    //12- Proje isterlerine göre doktor kaydediliyor mu? (4 puan)

    public ResultData<DoctorResponse> save(DoctorSaveRequest doctorSaveRequest) {
        Doctor saveDoctor = this.modelMapper.forRequest().map(doctorSaveRequest, Doctor.class);
        this.doctorRepo.save(saveDoctor);
        return ResultHelper.created(this.modelMapper.forResponse().map(saveDoctor, DoctorResponse.class));
    }


    public ResultData<DoctorResponse> update(DoctorUpdateRequest doctorUpdateRequest) {
        // Güncellenmek istenen doktorun mevcut olup olmadığını kontrolü
        Doctor existingDoctor = doctorRepo.findById(doctorUpdateRequest.getId())
                .orElseThrow(() -> new DoctorNotFoundException("Güncellenmek istenen doktor bulunamadı. ID: " + doctorUpdateRequest.getId()));

        modelMapper.forRequest().map(doctorUpdateRequest, existingDoctor);
        doctorRepo.save(existingDoctor);
        return ResultHelper.success(modelMapper.forResponse().map(existingDoctor, DoctorResponse.class));
    }



    public Result delete(@PathVariable Long id) {
        Doctor doctorDelete = doctorRepo.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Silmeye Çalıştığınız kullanıcı bulunamadı"));

        doctorRepo.delete(doctorDelete);
        return ResultHelper.ok();

    }


}
