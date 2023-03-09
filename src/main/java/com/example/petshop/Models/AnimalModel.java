package com.example.petshop.Models;

import com.example.petshop.Enums.Fur;
import com.example.petshop.Enums.StatusAnimal;
import com.example.petshop.Enums.Treatment;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "animals")
public class AnimalModel implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false, updatable = false)
    private Long id;

    @Column(nullable = false)
    private String animalName;
    @Column(nullable = false)
    private String species;
    @Column(nullable = false)
    private String breed;
    private Float height;
    private Float weight;
    private Fur fur;
    @Column(nullable = false)
    private Treatment treatment;
    @Column(nullable = false)
    private StatusAnimal statusAnimal;
    @Column(nullable = false)
    private String responsibleName;
    @Column(nullable = false)
    private String responsiblePhone;
    @Column(nullable = false)
    @Email
    private String responsibleEmail;
    private LocalDateTime sendDate;
    private LocalDateTime updateDate;

}
