package dev.patika.VetClinic.dto.customer;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OnlyCustomerResponse {
    private Long id;
    private String name;
    private String phone;
    private String email;
    private String address;
    private String city;

}
