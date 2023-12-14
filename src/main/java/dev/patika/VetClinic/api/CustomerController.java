package dev.patika.VetClinic.api;

import dev.patika.VetClinic.core.result.Result;
import dev.patika.VetClinic.dto.request.CustomerUpdateRequest;
import dev.patika.VetClinic.service.CustomerService;
import dev.patika.VetClinic.core.result.ResultData;
import dev.patika.VetClinic.dto.request.CustomerSaveRequest;
import dev.patika.VetClinic.dto.response.CustomerResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/customers")
@RequiredArgsConstructor
public class CustomerController {

    @Autowired
    private final CustomerService customerService;

    @GetMapping("/all")
    @ResponseStatus(HttpStatus.OK)
    public ResultData<List<CustomerResponse>> findAll() {
        return customerService.findAll();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResultData<CustomerResponse> findById(@PathVariable("id") Long id) {
        return customerService.findById(id);
    }

    @GetMapping("/name/{name}")
    @ResponseStatus(HttpStatus.OK)
    public ResultData<CustomerResponse> findByName(@PathVariable("name") String name) {
        return this.customerService.findByName(name);
    }



    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public ResultData<CustomerResponse> save(@Valid @RequestBody CustomerSaveRequest customerSaveRequest) {
       return customerService.save(customerSaveRequest);
    }

    @PutMapping()
    @ResponseStatus(HttpStatus.OK)
    public ResultData<CustomerResponse> update(@Valid @RequestBody CustomerUpdateRequest customerUpdateRequest){
        return customerService.update(customerUpdateRequest);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Result delete(@PathVariable("id") Long id) {
        return customerService.delete(id);
    }


}
