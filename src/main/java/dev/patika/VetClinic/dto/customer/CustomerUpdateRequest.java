package dev.patika.VetClinic.dto.customer;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerUpdateRequest {

    private long id;
    @NotNull(message = "Customer adı boş olamaz")
    private String name;

    @NotNull(message = "Telefon numarası boş olamaz")
    private String phone;

    private String mail;

    private String address;

    private String city;


}
