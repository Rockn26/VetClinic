package dev.patika.VetClinic.service;

import dev.patika.VetClinic.core.config.ModelMapper.IModelMapperService;
import dev.patika.VetClinic.dao.IAnimalRepo;
import dev.patika.VetClinic.dto.animal.AnimalResponse;
import dev.patika.VetClinic.dto.animal.AnimalSaveRequest;
import dev.patika.VetClinic.dto.animal.AnimalUpdateRequest;
import dev.patika.VetClinic.entities.Animal;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AnimalService {

    private final IAnimalRepo animalRepo;
    private final IModelMapperService modelMapper;

    public List<AnimalResponse> getAll() {
        List<Animal> animals = animalRepo.findAll();

        return animals.stream().map(animal -> modelMapper
                        .forResponse()
                        .map(animal, AnimalResponse.class))
                .collect(Collectors.toList());
    }

    public Animal getById(Long id) {
        return animalRepo.findById(id)
                .orElseThrow(()->new EntityNotFoundException("Entity with id " + id + " NOT FOUND"));
    }

    public AnimalResponse getResponseById(Long id) {
       return modelMapper
               .forResponse()
               .map(getById(id), AnimalResponse.class);
    }

    public Animal getByName(String name) {
        return animalRepo.findByName(name)
                .orElseThrow(()->new EntityNotFoundException("Entity with name " + name + " NOT FOUND"));

    }

    public AnimalResponse getResponseByName(String name) {
        return modelMapper
                .forResponse()
                .map(getByName(name), AnimalResponse.class);
    }

    public List<AnimalResponse> getResponseByCustomerId(Long customerId) {
        List<Animal> animals = animalRepo.findByCustomerId(customerId);

        return animals.stream()
                .map(animal -> modelMapper
                .forResponse()
                .map(animal, AnimalResponse.class)).toList();

    }

    public AnimalResponse create(AnimalSaveRequest animalSaveRequest) {
        Animal saveAnimal = this.modelMapper
                .forRequest()
                .map(animalSaveRequest, Animal.class);

        return modelMapper
                .forResponse()
                .map(animalRepo.save(saveAnimal), AnimalResponse.class);
    }

    public AnimalResponse update(AnimalUpdateRequest animalUpdateRequest) {
        Animal doesAnimalExist = getById(animalUpdateRequest.getId());

        modelMapper
                .forRequest()
                .map(animalUpdateRequest, doesAnimalExist);

        return modelMapper
                .forResponse()
                .map(animalRepo.save(doesAnimalExist), AnimalResponse.class);
    }

    public void delete(Long id) {
       animalRepo.delete(getById(id));
    }
}
