package com.example.petshop.Dtos;

import com.example.petshop.Enums.Role;
import com.example.petshop.Models.UserModel;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UserDto {

    @NotBlank
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Long id;
    @NotBlank
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String firstname;
    @NotBlank
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String lastname;
    @NotBlank
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String email;
    @NotBlank
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Role role;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String message;


    public UserDto (UserModel userModel) {
        this.id = userModel.getId();
        this.firstname = userModel.getFirstname();
        this.lastname = userModel.getLastname();
        this.email = userModel.getEmail();
        this.role = userModel.getRole();
    }

    public UserDto(String message) {
        this.message = message;
    }

    public UserDto() {

    }
}
