package dev.patika.VetClinic.service;

import dev.patika.VetClinic.core.config.ModelMapper.IModelMapperService;
import dev.patika.VetClinic.core.exception.CustomerNotFoundException;
import dev.patika.VetClinic.core.result.Result;
import dev.patika.VetClinic.core.result.ResultData;
import dev.patika.VetClinic.core.utilies.Msg;
import dev.patika.VetClinic.core.utilies.ResultHelper;
import dev.patika.VetClinic.dao.CustomerRepo;
import dev.patika.VetClinic.dto.request.CustomerSaveRequest;
import dev.patika.VetClinic.dto.request.CustomerUpdateRequest;
import dev.patika.VetClinic.dto.response.AnimalResponse;
import dev.patika.VetClinic.dto.response.CustomerResponse;
import dev.patika.VetClinic.entities.Animal;
import dev.patika.VetClinic.entities.Customer;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CustomerService {

    @Autowired
    private final CustomerRepo customerRepo;
    private final IModelMapperService modelMapper;


    public ResultData<List<CustomerResponse>> findAll() {
        List<Customer> customers = this.customerRepo.findAll();
        List<CustomerResponse> customerResponses = customers.stream()
                .map(customer -> this.modelMapper.forResponse().map(customer, CustomerResponse.class))
                .collect(Collectors.toList());
        return ResultHelper.success(customerResponses);
    }


    public ResultData<CustomerResponse> findById(Long id) {
        Optional<Customer> optionalCustomer = Optional.ofNullable((this.customerRepo.findById(id).orElseThrow(() -> new CustomerNotFoundException("Böyle bir id numaralı kullanıcı yok "))));
        if (optionalCustomer.isPresent()) {
            Customer customer = optionalCustomer.get();
            CustomerResponse customerResponse = this.modelMapper.forResponse().map(customer, CustomerResponse.class);
            return ResultHelper.success(customerResponse);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Customer not found");
        }
    }

    //17- Hayvan sahipleri isme göre filtreleniyor mu?
    public ResultData<CustomerResponse> findByName(String name) {
        Optional<Customer> optionalCustomer = this.customerRepo.findByName(name);
        if (optionalCustomer.isPresent()) {
            Customer customer = optionalCustomer.get();
            CustomerResponse customerResponse = this.modelMapper.forResponse().map(customer, CustomerResponse.class);
            return ResultHelper.success(customerResponse);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Customer not found with name " + name);
        }
    }

    //10- Proje isterlerine göre hayvan sahibi kaydediliyor mu?

    public ResultData<CustomerResponse> save(CustomerSaveRequest customer) {
        Customer saveCustomer = this.modelMapper.forRequest().map(customer, Customer.class);
        this.customerRepo.save(saveCustomer);
        return ResultHelper.created(this.modelMapper.forResponse().map(saveCustomer, CustomerResponse.class));
    }

    //25- Exception kullanılmış mı? (id ile güncelleme, silme işlemlerinde girilen id’de veri yoksa hata fırlatma gibi)
    public ResultData<CustomerResponse> update(CustomerUpdateRequest customerRequest) {
        // Güncellenmek istenen müşterinin mevcut olup olmadığı kontrolü
        Customer existingCustomer = customerRepo.findById(customerRequest.getId())
                .orElseThrow(() -> new CustomerNotFoundException("Güncellenmek istenen müşteri bulunamadı. ID: " + customerRequest.getId()));

        modelMapper.forRequest().map(customerRequest, existingCustomer);
        customerRepo.save(existingCustomer);


        return ResultHelper.success(modelMapper.forResponse().map(existingCustomer, CustomerResponse.class));
    }


    public Result delete(@PathVariable Long id) {
        Customer customerDelete = customerRepo.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Silmeye Çalıştığınız kullanıcı bulunamadı"));

        customerRepo.delete(customerDelete);
        return ResultHelper.ok();
    }


}
