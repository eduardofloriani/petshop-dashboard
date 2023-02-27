package com.example.petshop.Controllers;

import com.example.petshop.Dtos.AuthenticationResponse;
import com.example.petshop.Dtos.UserDto;
import com.example.petshop.Exceptions.RepeatedEmailException;
import com.example.petshop.Dtos.AuthenticationRequest;
import com.example.petshop.Dtos.RegisterRequest;
import com.example.petshop.Service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "*")
public class AuthenticationController {

    @Autowired
    private AuthenticationService service;

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody RegisterRequest request) {
        try {
            AuthenticationResponse newUser = service.register(request);
            return ResponseEntity.ok(newUser);
        } catch (RepeatedEmailException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new AuthenticationResponse(e.getMessage()));
        }
    }

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request) {
        return ResponseEntity.status(HttpStatus.OK).body(service.authenticate(request));
    }
}
