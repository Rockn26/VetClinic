package dev.patika.VetClinic.service;


import dev.patika.VetClinic.core.config.ModelMapper.IModelMapperService;
import dev.patika.VetClinic.dao.IAppointmentRepo;
import dev.patika.VetClinic.dao.IReportRepo;
import dev.patika.VetClinic.dto.report.ReportResponse;
import dev.patika.VetClinic.dto.report.ReportSaveRequest;
import dev.patika.VetClinic.dto.report.ReportUpdateRequest;
import dev.patika.VetClinic.entities.Appointment;
import dev.patika.VetClinic.entities.Report;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReportService {

    private final IReportRepo reportRepo;
    private final IAppointmentRepo appointmentRepo; // AppointmentRepo enjekte edildi
    private final IModelMapperService modelMapper;


    public List<ReportResponse> getAll() {
        List<Report> reports = reportRepo.findAll();

        return reports.stream().map(report -> modelMapper
                        .forResponse()
                        .map(report, ReportResponse.class))
                .collect(Collectors.toList());
    }

    public Report getById(Long id) {
        return reportRepo.findById(id)
                .orElseThrow(()->new EntityNotFoundException("Entity with id " + id + " NOT FOUND"));
    }

    public ReportResponse getResponseById(Long id) {
        return modelMapper
                .forResponse()
                .map(getById(id), ReportResponse.class);
    }

    public ReportResponse create(ReportSaveRequest reportSaveRequest) {
        Report saveReport = this.modelMapper
                .forRequest()
                .map(reportSaveRequest, Report.class);

        return modelMapper
                .forResponse()
                .map(reportRepo.save(saveReport), ReportResponse.class);
    }

    public ReportResponse update(ReportUpdateRequest reportUpdateRequest) {
        // Mevcut raporu ID'ye göre bul
        Report doesReportExist = getById(reportUpdateRequest.getId());

        Report report = modelMapper
                .forResponse()
                .map(reportUpdateRequest, Report.class);

        modelMapper
                .forRequest()
                .map(report, doesReportExist);

        return modelMapper
                .forResponse()
                .map(reportRepo.save(doesReportExist), ReportResponse.class);







    }



    public void delete(Long id) {
        reportRepo.delete(getById(id));
    }
}