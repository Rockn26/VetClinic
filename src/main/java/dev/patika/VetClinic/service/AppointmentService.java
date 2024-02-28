package dev.patika.VetClinic.service;


import dev.patika.VetClinic.core.config.ModelMapper.IModelMapperService;
import dev.patika.VetClinic.core.exception.DoctorIsNotAvailableException;
import dev.patika.VetClinic.core.exception.HourConflictException;
import dev.patika.VetClinic.dao.IAppointmentRepo;
import dev.patika.VetClinic.dto.appointment.AppointmentResponse;
import dev.patika.VetClinic.dto.appointment.AppointmentSaveRequest;
import dev.patika.VetClinic.dto.appointment.AppointmentUpdateRequest;
import dev.patika.VetClinic.entities.Appointment;
import dev.patika.VetClinic.entities.AvailableDate;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AppointmentService {
    private final IAppointmentRepo appointmentRepo;
    private final IModelMapperService modelMapper;
    private final AvailableDateService availableDateService;

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
        checkIfDoctorIsAvailable(appointmentSaveRequest);
        checkHourConflict(appointmentSaveRequest);

        Appointment saveAppointment = this.modelMapper
                .forRequest()
                .map(appointmentSaveRequest, Appointment.class);

        return modelMapper
                .forResponse()
                .map(appointmentRepo.save(saveAppointment), AppointmentResponse.class);
    }

    private void checkIfDoctorIsAvailable(AppointmentSaveRequest appointmentSaveRequest) {
        Optional<AvailableDate> availableDateMayBePresent = availableDateService
                .getByAvailableDate(appointmentSaveRequest.getAppointmentDate().toLocalDate());

       if (availableDateMayBePresent.isEmpty())
           throw new DoctorIsNotAvailableException(
                   "Doctor is not available at this date: " +
                           appointmentSaveRequest.getAppointmentDate().toLocalDate()
           );
    }

    private void checkHourConflict(AppointmentSaveRequest appointmentSaveRequest) {
        Optional<Appointment> appointmentMayBePresent = appointmentRepo
                .findByDoctorIdAndAppointmentDate(
                        appointmentSaveRequest.getDoctor().getId(),
                        appointmentSaveRequest.getAppointmentDate()
                );

        if (appointmentMayBePresent.isPresent())
            throw new HourConflictException(
                    "Doctir is not available at this time: " +
                            appointmentSaveRequest.getAppointmentDate()
            );
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