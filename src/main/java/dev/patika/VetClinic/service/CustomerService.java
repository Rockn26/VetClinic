package dev.patika.VetClinic.service;

import dev.patika.VetClinic.core.config.ModelMapper.IModelMapperService;
import dev.patika.VetClinic.dao.ICustomerRepo;
import dev.patika.VetClinic.dto.customer.CustomerResponse;
import dev.patika.VetClinic.dto.customer.CustomerSaveRequest;
import dev.patika.VetClinic.dto.customer.CustomerUpdateRequest;
import dev.patika.VetClinic.entities.Customer;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final ICustomerRepo customerRepo;
    private final IModelMapperService modelMapper;

    public List<CustomerResponse> getAll() {
        List<Customer> customers = customerRepo.findAll();

        return customers.stream().map(customer -> modelMapper
                        .forResponse()
                        .map(customer, CustomerResponse.class))
                .collect(Collectors.toList());
    }

    public Customer getById(Long id) {
        return customerRepo.findById(id)
                .orElseThrow(()->new EntityNotFoundException("Entity with id " + id + " NOT FOUND"));
    }

    public CustomerResponse getResponseById(Long id) {
        return modelMapper
                .forResponse()
                .map(getById(id), CustomerResponse.class);
    }

    public Customer getByName(String name) {
        return customerRepo.findByName(name)
                .orElseThrow(()->new EntityNotFoundException("Entity with name " + name + " NOT FOUND"));

    }

    public CustomerResponse getResponseByName(String name) {
        return modelMapper
                .forResponse()
                .map(getByName(name), CustomerResponse.class);
    }

    public CustomerResponse create(CustomerSaveRequest customerSaveRequest) {
        Customer saveCustomer = this.modelMapper
                .forRequest()
                .map(customerSaveRequest, Customer.class);

        return modelMapper
                .forResponse()
                .map(customerRepo.save(saveCustomer), CustomerResponse.class);
    }

    public CustomerResponse update(CustomerUpdateRequest customerUpdateRequest) {
        Customer doesCustomerExist = getById(customerUpdateRequest.getId());

        modelMapper
                .forRequest()
                .map(customerUpdateRequest, doesCustomerExist);

        return modelMapper
                .forResponse()
                .map(customerRepo.save(doesCustomerExist), CustomerResponse.class);
    }

    public void delete(Long id) {
        customerRepo.delete(getById(id));
    }
}