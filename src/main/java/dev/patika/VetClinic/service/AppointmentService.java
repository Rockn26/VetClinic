package dev.patika.VetClinic.service;


import dev.patika.VetClinic.core.config.ModelMapper.IModelMapperService;
import dev.patika.VetClinic.core.exception.CustomerNotFoundException;
import dev.patika.VetClinic.core.exception.VetNotFoundException;
import dev.patika.VetClinic.core.result.Result;
import dev.patika.VetClinic.core.result.ResultData;
import dev.patika.VetClinic.core.utilies.ResultHelper;
import dev.patika.VetClinic.dao.IAnimalRepo;
import dev.patika.VetClinic.dao.IAppointmentRepo;
import dev.patika.VetClinic.dao.IDoctorRepo;
import dev.patika.VetClinic.dto.request.AppointmentSaveRequest;
import dev.patika.VetClinic.dto.request.AppointmentUpdateRequest;
import dev.patika.VetClinic.dto.request.CustomerSaveRequest;
import dev.patika.VetClinic.dto.request.CustomerUpdateRequest;
import dev.patika.VetClinic.dto.response.AppointmentResponse;
import dev.patika.VetClinic.dto.response.CustomerResponse;
import dev.patika.VetClinic.dto.response.VaccineResponse;
import dev.patika.VetClinic.entities.Appointment;
import dev.patika.VetClinic.entities.Customer;
import dev.patika.VetClinic.entities.Vaccine;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AppointmentService {

    private final IAppointmentRepo appointmentRepo;
    private final IModelMapperService modelMapper;
    private final AnimalService animalService;
    private final DoctorService doctorService;

    public ResultData<List<AppointmentResponse>> findAll() {
        List<Appointment> appointments = this.appointmentRepo.findAll();
        List<AppointmentResponse> appointmentResponses = appointments.stream()
                .map(appointment -> this.modelMapper.forResponse().map(appointment, AppointmentResponse.class))
                .collect(Collectors.toList());
        return ResultHelper.success(appointmentResponses);
    }


    public ResultData<AppointmentResponse> findById(Long id) {
        Optional<Appointment> optionalAppointment = Optional.ofNullable((this.appointmentRepo.findById(id).orElseThrow(() -> new VetNotFoundException("Böyle bir randevu yok "))));
        if (optionalAppointment.isPresent()) {
            Appointment appointment = optionalAppointment.get();
            AppointmentResponse appointmentResponse = this.modelMapper.forResponse().map(appointment, AppointmentResponse.class);
            return ResultHelper.success(appointmentResponse);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Appointment not found");
        }
    }

    //24- Randevular kullanıcı tarafından girilen tarih aralığına ve doktora göre filtreleniyor mu?
    public ResultData<List<AppointmentResponse>> findAppointmentsByDoctorAndDateRange(Long doctorId, LocalDateTime startDate, LocalDateTime endDate) {
        List<Appointment> appointments = appointmentRepo.findByDoctorIdAndAppointmentDateBetween(doctorId, startDate, endDate);
        List<AppointmentResponse> responses = appointments.stream()
                .map(appointment -> modelMapper.forResponse().map(appointment, AppointmentResponse.class))
                .collect(Collectors.toList());
        return ResultHelper.success(responses);
    }


    //23- Randevular kullanıcı tarafından girilen tarih aralığına ve hayvana göre filtreleniyor mu?
    public ResultData<List<AppointmentResponse>> findByAnimalAndDateRange(Long animalId, LocalDateTime startDate, LocalDateTime endDate) {
        List<Appointment> appointments = appointmentRepo.findByAnimalIdAndAppointmentDateBetween(animalId, startDate, endDate);
        List<AppointmentResponse> responses = appointments.stream()
                .map(appointment -> modelMapper.forResponse().map(appointment, AppointmentResponse.class))
                .collect(Collectors.toList());
        return ResultHelper.success(responses);
    }



    //14- Proje isterlerine göre randevu kaydediliyor mu?
    public ResultData<AppointmentResponse> save(AppointmentSaveRequest appointmentSaveRequest) {
        Long animalId = appointmentSaveRequest.getAnimal().getId();
        Long doctorId = appointmentSaveRequest.getDoctor().getId();
        if (!isEntityExists(animalId) && !isEntityExists(doctorId)) {
            return ResultHelper.badRequest("Belirtilen hayvan veya doktor bulunamadı. Geçerli bir hayvan ID'si veya doktor ID'si sağladığınızdan emin olun.");
        }

        // Randevu zamanı ve doktor kontrolü
        if (!isDoctorAvailable(appointmentSaveRequest.getDoctor().getId(), appointmentSaveRequest.getAppointmentDate())) {
            throw new RuntimeException();
        }
        Appointment saveAppointment = this.modelMapper.forRequest().map(appointmentSaveRequest, Appointment.class);
        this.appointmentRepo.save(saveAppointment);
        return ResultHelper.created(this.modelMapper.forResponse().map(saveAppointment, AppointmentResponse.class));
    }


    public ResultData<AppointmentResponse> update(AppointmentUpdateRequest appointmentUpdateRequest) {
        Appointment updateAppointment = this.modelMapper.forRequest().map(appointmentUpdateRequest, Appointment.class);

        this.appointmentRepo.save(updateAppointment);
        return ResultHelper.success(this.modelMapper.forResponse().map(updateAppointment, AppointmentResponse.class));
    }


    public Result delete(Long id) {
        Appointment appointmentDelete = appointmentRepo.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Silmeye Çalıştığınız id bulunamadı"));

        appointmentRepo.delete(appointmentDelete);
        return ResultHelper.ok();
    }


    private boolean isEntityExists(Long entityId) {
        return animalService.findById(entityId).isStatus() || doctorService.findByID(entityId).isStatus();
    }

    private boolean isDoctorAvailable(Long doctorId, LocalDateTime appointmentDate) {
        // Doktorun müsaitliğini kontrol etme mantığı

        // Aynı doktor için aynı tarih ve saatte başka bir randevunun olup olmadığını kontrol etme
        return !appointmentRepo.existsByDoctorIdAndAppointmentDate(doctorId, appointmentDate);
    }



}
