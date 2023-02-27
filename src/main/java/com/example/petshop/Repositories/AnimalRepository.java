package com.example.petshop.Repositories;

import com.example.petshop.Models.AnimalModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface AnimalRepository extends JpaRepository<AnimalModel, Long> {
    AnimalModel findAnimalById(Long id);

    boolean existsByAnimalName(String animalName);
    boolean existsByResponsibleName(String responsibleName);
    boolean existsById(Long id);
}
