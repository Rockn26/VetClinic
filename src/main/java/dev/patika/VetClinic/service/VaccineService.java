package dev.patika.VetClinic.service;


import dev.patika.VetClinic.core.config.ModelMapper.IModelMapperService;
import dev.patika.VetClinic.core.exception.VaccineNotFoundException;
import dev.patika.VetClinic.core.exception.VetNotFoundException;
import dev.patika.VetClinic.core.result.Result;
import dev.patika.VetClinic.core.result.ResultData;
import dev.patika.VetClinic.core.utilies.ResultHelper;
import dev.patika.VetClinic.dao.IAnimalRepo;
import dev.patika.VetClinic.dao.IVaccineRepo;
import dev.patika.VetClinic.dto.request.VaccineSaveRequest;
import dev.patika.VetClinic.dto.request.VaccineUpdateRequest;
import dev.patika.VetClinic.dto.response.VaccineResponse;
import dev.patika.VetClinic.entities.Animal;
import dev.patika.VetClinic.entities.Vaccine;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class VaccineService {

    private final IVaccineRepo vaccineRepo;
    private final IModelMapperService modelMapper;
    private final AnimalService animalService;
    private final IAnimalRepo animalRepo;

    public ResultData<List<VaccineResponse>> findAll() {
        List<Vaccine> vaccines = this.vaccineRepo.findAll();
        List<VaccineResponse> vaccineResponses = vaccines.stream()
                .map(vaccine -> this.modelMapper.forResponse().map(vaccine, VaccineResponse.class))
                .collect(Collectors.toList());
        return ResultHelper.success(vaccineResponses);
    }


    //25- Exception kullanılmış mı? (id ile güncelleme, silme işlemlerinde girilen id’de veri yoksa hata fırlatma gibi) (4 Puan)
    public ResultData<VaccineResponse> findById(Long id) {
        Optional<Vaccine> optionalVaccine = Optional.ofNullable((this.vaccineRepo.findById(id).orElseThrow(() -> new VaccineNotFoundException("Böyle bir id numaralı aşı yok "))));
        if (optionalVaccine.isPresent()) {
            Vaccine vaccine = optionalVaccine.get();
            VaccineResponse vaccineResponse = this.modelMapper.forResponse().map(vaccine, VaccineResponse.class);
            return ResultHelper.success(vaccineResponse);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Vaccine not found");
        }
    }


    //20- Belirli bir hayvana ait tüm aşı kayıtları listelenebiliyor mu? (4 Puan)
    public ResultData<List<VaccineResponse>> findAllByAnimalId(Long animalId) {
        Animal animal = animalRepo.findById(animalId)
                .orElseThrow(() -> new VetNotFoundException("Hayvan bulunamadı, ID: " + animalId));
        // İlgili hayvana ait aşı kayıtlarını getir
        List<Vaccine> vaccines = this.vaccineRepo.findByAnimalId(animalId);

        // Aşı kayıtlarını VaccineResponse nesnelerine dönüşümü
        List<VaccineResponse> vaccineResponses = vaccines.stream()
                .map(vaccine -> this.modelMapper.forResponse().map(vaccine, VaccineResponse.class))
                .collect(Collectors.toList());

        return ResultHelper.success(vaccineResponses);
    }


    //21- Hayvanların aşı kayıtları, girilen tarih aralığına göre doğru şekilde listeleniyor mu? (4 Puan)
    public ResultData<List<VaccineResponse>> findVaccinesByProtectionDateRange(LocalDate startDate, LocalDate endDate) {
        List<Vaccine> vaccinesInDateRange = vaccineRepo.findByProtectionFinishDateBetween(startDate, endDate);
        List<VaccineResponse> vaccineResponses = vaccinesInDateRange.stream()
                .map(vaccine -> modelMapper.forResponse().map(vaccine, VaccineResponse.class))
                .collect(Collectors.toList());
        return ResultHelper.success(vaccineResponses);
    }



    //15- Proje isterlerine göre hayvana ait aşı kaydediliyor mu? (4 puan)
    //19- Yeni aşı kaydetme işleminde koruyuculuk bitiş tarihi kontrolü yapılmış mı? (4 Puan)
    public ResultData<VaccineResponse> save(VaccineSaveRequest vaccineSaveRequest) {
        String vaccineName = vaccineSaveRequest.getName();
        String vaccineCode = vaccineSaveRequest.getCode();
        LocalDate expirationDate = vaccineSaveRequest.getProtectionFinishDate();

        // Aynı tip aşıları ve bitiş tarihini kontrol et
        List<Vaccine> existingVaccines = vaccineRepo.findExistingVaccines(vaccineName, vaccineCode, expirationDate);

        // Aşının bitiş tarihini kontrol et
        if (existingVaccines.isEmpty()) {
            Vaccine saveVaccine = this.modelMapper.forRequest().map(vaccineSaveRequest, Vaccine.class);
            this.vaccineRepo.save(saveVaccine);
            return ResultHelper.created(this.modelMapper.forResponse().map(saveVaccine, VaccineResponse.class));
        } else {
            // Aynı tip aşı bulundu ve bitiş tarihi geçmemiş
            return ResultHelper.badRequest("Aynı tip aşı zaten kayıtlı ve bitiş tarihi geçmemiş.");
        }
    }

    public ResultData<VaccineResponse> update(VaccineUpdateRequest vaccineUpdateRequest) {
        // Güncellenmek istenen Vaccine'in mevcut olup olmadığını kontrolü
        Vaccine existingVaccine = vaccineRepo.findById(vaccineUpdateRequest.getId())
                .orElseThrow(() -> new VaccineNotFoundException("Güncellenmek istenen aşı bulunamadı. ID: " + vaccineUpdateRequest.getId()));

        modelMapper.forRequest().map(vaccineUpdateRequest, existingVaccine);
        vaccineRepo.save(existingVaccine);
        return ResultHelper.success(modelMapper.forResponse().map(existingVaccine, VaccineResponse.class));
    }



    public Result delete(Long id) {
        Vaccine vaccineDelete = vaccineRepo.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Silmeye Çalıştığınız kullanıcı bulunamadı"));

        vaccineRepo.delete(vaccineDelete);
        return ResultHelper.ok();
    }






}
