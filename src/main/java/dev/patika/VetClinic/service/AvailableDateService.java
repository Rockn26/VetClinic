package dev.patika.VetClinic.service;

import dev.patika.VetClinic.core.config.ModelMapper.IModelMapperService;
import dev.patika.VetClinic.core.exception.AvailableDateNotFoundException;
import dev.patika.VetClinic.core.exception.VetNotFoundException;
import dev.patika.VetClinic.core.result.Result;
import dev.patika.VetClinic.core.result.ResultData;
import dev.patika.VetClinic.core.utilies.ResultHelper;
import dev.patika.VetClinic.dao.IAvailableDateRepo;
import dev.patika.VetClinic.dto.request.AvailableDateSaveRequest;
import dev.patika.VetClinic.dto.request.AvailableDateUpdateRequest;
import dev.patika.VetClinic.dto.request.CustomerUpdateRequest;
import dev.patika.VetClinic.dto.response.*;
import dev.patika.VetClinic.entities.*;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class AvailableDateService {

    @Autowired
    private final IAvailableDateRepo availableDateRepo;
    private final IModelMapperService modelMapper;
    private final DoctorService doctorService;


    public ResultData<List<AvailableDateResponse>> findAll() {
        List<AvailableDate> availableDates = this.availableDateRepo.findAll();
        List<AvailableDateResponse> availableDateResponses = availableDates.stream()
                .map(availableDate -> this.modelMapper.forResponse().map(availableDate, AvailableDateResponse.class))
                .collect(Collectors.toList());
        return ResultHelper.success(availableDateResponses);
    }


    //25- Exception kullanılmış mı? (id ile güncelleme, silme işlemlerinde girilen id’de veri yoksa hata fırlatma gibi) (4 Puan)

    public ResultData<AvailableDateResponse> findById(Long id) {
        Optional<AvailableDate> optionalAvailableDate = this.availableDateRepo.findById(id);
        if (optionalAvailableDate.isPresent()) {
            AvailableDate availableDate = optionalAvailableDate.get();
            AvailableDateResponse availableDateResponse = this.modelMapper.forResponse().map(availableDate, AvailableDateResponse.class);
            return ResultHelper.success(availableDateResponse);
        } else {
            throw new VetNotFoundException("AvailableDate not found");
        }
    }


    //13- Proje isterlerine göre doktor müsait günü kaydediliyor mu?

    public ResultData<AvailableDateResponse> save(AvailableDateSaveRequest availableDateSaveRequest) {

        Long doctorId = availableDateSaveRequest.getDoctorId();
        if (!isDoctorExists(doctorId)) {
            // Mevcut olmayan bir doktor ID'siyle kayıt yapmaya çalışıldığında hata mesajı
            return ResultHelper.badRequest("Belirtilen doktor bulunamadı. Geçerli bir doktor ID'si sağladığınızdan emin olun.");
        }
        AvailableDate saveAvailableDate = this.modelMapper.forRequest().map(availableDateSaveRequest, AvailableDate.class);
        this.availableDateRepo.save(saveAvailableDate);
        return ResultHelper.created(this.modelMapper.forResponse().map(saveAvailableDate, AvailableDateResponse.class));
    }

    public ResultData<AvailableDateResponse> update(AvailableDateUpdateRequest availableDateUpdateRequest) {
        // Güncellenmek istenen AvailableDate'in mevcut olup olmadığını kontrol edin
        AvailableDate existingAvailableDate = availableDateRepo.findById(availableDateUpdateRequest.getId())
                .orElseThrow(() -> new AvailableDateNotFoundException("Güncellenmek istenen tarih bulunamadı. ID: " + availableDateUpdateRequest.getId()));

        modelMapper.forRequest().map(availableDateUpdateRequest, existingAvailableDate);
        availableDateRepo.save(existingAvailableDate);
        return ResultHelper.success(modelMapper.forResponse().map(existingAvailableDate, AvailableDateResponse.class));
    }



    public Result delete(Long id) {
        AvailableDate availableDateDelete = availableDateRepo.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Silmeye Çalıştığınız kullanıcı bulunamadı"));

        availableDateRepo.delete(availableDateDelete);
        return ResultHelper.ok();
    }


    private boolean isDoctorExists(Long doctorId) {
        return doctorService.findByID(doctorId).isStatus();
    }


}
