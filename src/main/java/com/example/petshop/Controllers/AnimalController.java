package com.example.petshop.Controllers;

import com.example.petshop.Enums.StatusAnimal;
import com.example.petshop.Models.AnimalModel;
import com.example.petshop.Service.AnimalService;
import com.example.petshop.Service.EmailService;
import jakarta.persistence.PreUpdate;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/animals")
public class AnimalController {

    @Autowired
    AnimalService animalService;

    @Autowired
    EmailService emailService;

    @GetMapping("")
    public ResponseEntity<List<AnimalModel>> getAllAnimals () {
        List<AnimalModel> allAnimals = animalService.findAllAnimals();
        return ResponseEntity.status(HttpStatus.OK).body(allAnimals);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getAnimalById(@PathVariable("id") Long id) {
        if (animalService.existsById(id)) {
            AnimalModel animal = animalService.findAnimalById(id);
            return ResponseEntity.status(HttpStatus.OK).body(animal);
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Animal não encontrado.");
    }

    @PostMapping("/add")
    public ResponseEntity<Object> addNewAnimal(@RequestBody @Valid AnimalModel animalModel) {
        if ((animalService.existsByAnimalName(animalModel.getAnimalName()) &&
                (animalService.existsByResponsibleName(animalModel.getResponsibleName())))) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Você já cadastrou esse animal.");
        }

        animalModel.setStatusAnimal(StatusAnimal.PENDING);
        AnimalModel newAnimal = animalService.addAnimal(animalModel);
        return ResponseEntity.status(HttpStatus.OK).body(newAnimal);
    }

    @PutMapping("/update")
    public ResponseEntity<Object> updateAnimal(@RequestBody @Valid AnimalModel animalModel) {
        AnimalModel updatedAnimal = animalService.updateAnimal(animalModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(updatedAnimal);
    }

    @PutMapping("/finished-service")
    public ResponseEntity<Object> finishedService(@RequestBody @Valid AnimalModel animalModel) {
        animalModel.setStatusAnimal(StatusAnimal.FINISHED);
        emailService.sendEmail(animalModel);
        animalService.updateAnimal(animalModel);
        return ResponseEntity.status(HttpStatus.OK).body(animalModel);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Object> deleteAnimal(@PathVariable("id") Long id) {
        if (animalService.existsById(id)) {
            animalService.deleteAnimal(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Animal não encontrado.");
    }
}
