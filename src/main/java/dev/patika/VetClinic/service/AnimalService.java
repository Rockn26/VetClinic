package dev.patika.VetClinic.service;

import dev.patika.VetClinic.core.config.ModelMapper.IModelMapperService;
import dev.patika.VetClinic.core.exception.AnimalNotFoundException;
import dev.patika.VetClinic.core.result.Result;
import dev.patika.VetClinic.core.result.ResultData;
import dev.patika.VetClinic.core.utilies.ResultHelper;
import dev.patika.VetClinic.dao.IAnimalRepo;
import dev.patika.VetClinic.dto.request.AnimalSaveRequest;
import dev.patika.VetClinic.dto.request.AnimalUpdateRequest;
import dev.patika.VetClinic.dto.response.AnimalResponse;
import dev.patika.VetClinic.dto.response.CustomerResponse;
import dev.patika.VetClinic.entities.Animal;
import dev.patika.VetClinic.entities.Customer;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AnimalService {

    private final IAnimalRepo animalRepo;
    private final IModelMapperService modelMapper;




    public ResultData<List<AnimalResponse>> findAll() {
        List<Animal> animals = this.animalRepo.findAll();
        List<AnimalResponse> animalResponses = animals.stream()
                .map(animal -> this.modelMapper.forResponse().map(animal, AnimalResponse.class))
                .collect(Collectors.toList());
        return ResultHelper.success(animalResponses);
    }




    //25- Exception kullanılmış mı? (id ile güncelleme, silme işlemlerinde girilen id’de veri yoksa hata fırlatma gibi
    public ResultData<AnimalResponse> findById(Long id) {
        Optional<Animal> optionalCustomer = Optional.ofNullable((this.animalRepo.findById(id).orElseThrow(() -> new AnimalNotFoundException("Hayvan Bulunamadı. ID: " + id))));
        if (optionalCustomer.isPresent()) {
            Animal animal = optionalCustomer.get();
            AnimalResponse animalResponse = this.modelMapper.forResponse().map(animal, AnimalResponse.class);
            return ResultHelper.success(animalResponse);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Animal not found");
        }
    }


    //16- Hayvanlar isme göre filtreleniyor mu? (4 Puan)
    public ResultData<AnimalResponse> findByName(String name) {
        Optional<Animal> optionalAnimal = Optional.ofNullable(this.animalRepo.findByName(name).orElseThrow(() -> new AnimalNotFoundException("Hayvan bulunamadı. İsim: " + name)));
        if (optionalAnimal.isPresent()) {
            Animal animal = optionalAnimal.get();
            AnimalResponse animalResponse = this.modelMapper.forResponse().map(animal, AnimalResponse.class);
            return ResultHelper.success(animalResponse);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Animal not found with name " + name);
        }
    }

    //18- Girilen hayvan sahibinin sistemde kayıtlı tüm hayvanlarını görüntüleme başarılı bir şekilde çalışıyor mu?
    public ResultData<List<AnimalResponse>> findByCustomerId(Long customerId) {
        List<Animal> animals = animalRepo.findByCustomerId(customerId); // Bu metot, müşteri kimliğine göre hayvanları getirir.
        List<AnimalResponse> animalResponses = animals.stream()
                .map(animal -> modelMapper.forResponse().map(animal, AnimalResponse.class))
                .collect(Collectors.toList());
        return ResultHelper.success(animalResponses);
    }



    // 11- Proje isterlerine göre hayvan kaydediliyor mu? (4 puan)
    public ResultData<AnimalResponse> save(AnimalSaveRequest animalSaveRequest) {
        Animal saveAnimal = this.modelMapper.forRequest().map(animalSaveRequest, Animal.class);
        this.animalRepo.save(saveAnimal);
        return ResultHelper.created(this.modelMapper.forResponse().map(saveAnimal, AnimalResponse.class));
    }


    public ResultData<AnimalResponse> update(AnimalUpdateRequest animalUpdateRequest) {
        // Güncellenmek istenen hayvanın mevcut olup olmadığının kontrolü
        Animal existingAnimal = animalRepo.findById(animalUpdateRequest.getId())
                .orElseThrow(() -> new AnimalNotFoundException("Güncellenmek istenen hayvan bulunamadı. ID: " + animalUpdateRequest.getId()));

        modelMapper.forRequest().map(animalUpdateRequest, existingAnimal);

        animalRepo.save(existingAnimal);

        return ResultHelper.success(modelMapper.forResponse().map(existingAnimal, AnimalResponse.class));
    }



    public Result delete(Long id) {
        Animal animalDelete = animalRepo.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Silmeye Çalıştığınız kullanıcı bulunamadı"));

        animalRepo.delete(animalDelete);
        return ResultHelper.ok();
    }

    public Customer mapCustomerResponseToCustomer(CustomerResponse customerResponse) {
        Customer customer = new Customer();

        customer.setId(customerResponse.getId());
        customer.setName(customerResponse.getName());
        customer.setAddress(customerResponse.getAddress());
        customer.setCity(customerResponse.getCity());
        customer.setMail(customerResponse.getMail());
        customer.setPhone(customerResponse.getPhone());

        return customer;
    }




}
