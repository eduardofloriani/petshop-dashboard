package com.example.petshop.Service;

import com.example.petshop.Models.AnimalModel;
import com.example.petshop.Repositories.AnimalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class AnimalService {

    @Autowired
    AnimalRepository animalRepository;

    public List<AnimalModel> findAllAnimals() {
        return animalRepository.findAll();
    }

    public AnimalModel findAnimalById(Long id) {
        return animalRepository.findAnimalById(id);
    }

    public AnimalModel addAnimal(AnimalModel animalModel) {
        animalModel.setSendDate(LocalDateTime.now());
        return animalRepository.save(animalModel);
    }

    public AnimalModel updateAnimal(AnimalModel animalModel) {
        animalModel.setUpdateDate(LocalDateTime.now());
        return animalRepository.save(animalModel);
    }

    public void deleteAnimal(Long id) {
        animalRepository.deleteById(id);
    }

    public boolean existsByAnimalName(String animalName) {
        return animalRepository.existsByAnimalName(animalName);
    }

    public boolean existsByResponsibleName(String responsibleName) {
        return animalRepository.existsByResponsibleName(responsibleName);
    }

    public boolean existsById(Long id) {
        return animalRepository.existsById(id);
    }

}
