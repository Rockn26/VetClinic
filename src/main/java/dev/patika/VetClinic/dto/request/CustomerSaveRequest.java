package dev.patika.VetClinic.dto.request;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.el.util.Validation;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerSaveRequest {

    @NotNull(message = "Customer adı boş olamaz")
    private String name;

    @NotNull(message = "Telefon numarası boş olamaz")
    private String phone;

    private String mail;

    private String address;

    private String city;

}
