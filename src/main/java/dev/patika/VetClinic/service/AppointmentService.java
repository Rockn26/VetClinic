package dev.patika.VetClinic.service;


import dev.patika.VetClinic.core.config.ModelMapper.IModelMapperService;
import dev.patika.VetClinic.dao.IAppointmentRepo;
import dev.patika.VetClinic.dto.appointment.AppointmentResponse;
import dev.patika.VetClinic.dto.appointment.AppointmentSaveRequest;
import dev.patika.VetClinic.dto.appointment.AppointmentUpdateRequest;
import dev.patika.VetClinic.entities.Appointment;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AppointmentService {

    private final IAppointmentRepo appointmentRepo;
    private final IModelMapperService modelMapper;

    public List<AppointmentResponse> getAll() {
        List<Appointment> appointments = appointmentRepo.findAll();

        return appointments.stream().map(appointment -> modelMapper
                        .forResponse()
                        .map(appointment, AppointmentResponse.class))
                .collect(Collectors.toList());
    }

    public Appointment getById(Long id) {
        return appointmentRepo.findById(id)
                .orElseThrow(()->new EntityNotFoundException("Entity with id " + id + " NOT FOUND"));
    }

    public AppointmentResponse getResponseById(Long id) {
        return modelMapper
                .forResponse()
                .map(getById(id), AppointmentResponse.class);
    }

    public AppointmentResponse create(AppointmentSaveRequest appointmentSaveRequest) {
        Appointment saveAppointment = this.modelMapper
                .forRequest()
                .map(appointmentSaveRequest, Appointment.class);

        return modelMapper
                .forResponse()
                .map(appointmentRepo.save(saveAppointment), AppointmentResponse.class);
    }

    public AppointmentResponse update(AppointmentUpdateRequest appointmentUpdateRequest) {
        Appointment doesAppointmentExist = getById(appointmentUpdateRequest.getId());

        modelMapper
                .forRequest()
                .map(appointmentUpdateRequest, doesAppointmentExist);

        return modelMapper
                .forResponse()
                .map(appointmentRepo.save(doesAppointmentExist), AppointmentResponse.class);
    }

    public void delete(Long id) {
        appointmentRepo.delete(getById(id));
    }
}