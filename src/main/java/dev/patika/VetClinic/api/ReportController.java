package dev.patika.VetClinic.api;


import dev.patika.VetClinic.dto.report.ReportSaveRequest;
import dev.patika.VetClinic.dto.report.ReportUpdateRequest;
import dev.patika.VetClinic.service.ReportService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/reports")
@RequiredArgsConstructor
public class ReportController {

    private final ReportService reportService;

    @GetMapping
    public ResponseEntity<?> getAll() {
        return new ResponseEntity<>(reportService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getResponseById(@PathVariable("id") Long id){
        return new ResponseEntity<>(reportService.getResponseById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody ReportSaveRequest reportSaveRequest) {
        return new ResponseEntity<>(reportService.create(reportSaveRequest), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<?> update(@Valid @RequestBody ReportUpdateRequest reportUpdateRequest){
        return new ResponseEntity<>(reportService.update(reportUpdateRequest),HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        reportService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}