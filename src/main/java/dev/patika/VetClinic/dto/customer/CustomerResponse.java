package dev.patika.VetClinic.dto.customer;

import dev.patika.VetClinic.dto.animal.OnlyAnimalResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerResponse {

    private Long id;
    private String name;
    private String phone;

    private String email;
    private String address;
    private String city;

    private List<OnlyAnimalResponse> animals;

}
