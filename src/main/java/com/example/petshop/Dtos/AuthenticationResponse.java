package com.example.petshop.Dtos;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationResponse {

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String token;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String message;

    public AuthenticationResponse(String message) {
        this.message = message;
    }

}
