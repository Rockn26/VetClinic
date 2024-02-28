package dev.patika.VetClinic.dto.report;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OnlyReportResponse {
    private Long id;
    private String title;
    private String diagnosis;
    private double price;
}
