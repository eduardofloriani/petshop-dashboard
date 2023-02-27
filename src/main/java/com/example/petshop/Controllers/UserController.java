package com.example.petshop.Controllers;

import com.example.petshop.Dtos.AuthenticationResponse;
import com.example.petshop.Dtos.UserDto;
import com.example.petshop.Models.UserModel;
import com.example.petshop.Service.AuthenticationService;
import com.example.petshop.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(value = "/user")
@CrossOrigin(origins = "*")
public class UserController {

    @Autowired
    private AuthenticationService service;

    @Autowired
    private UserService userService;

    @GetMapping("/")
    public ResponseEntity<UserDto> getUserInfo(@RequestHeader(HttpHeaders.AUTHORIZATION) String authorizationHeader) {
        UserDto user = userService.getUserInfo(authorizationHeader);
        return ResponseEntity.status(HttpStatus.OK).body(user);
    }

    @PutMapping("/update")
    public ResponseEntity<AuthenticationResponse> updateUser(@RequestBody UserModel userModel) {
        return ResponseEntity.ok(userService.updateUser(userModel));
    }

}
