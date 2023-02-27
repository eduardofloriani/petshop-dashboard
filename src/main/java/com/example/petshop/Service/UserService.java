package com.example.petshop.Service;

import com.example.petshop.Dtos.AuthenticationResponse;
import com.example.petshop.Dtos.UserDto;
import com.example.petshop.Exceptions.UserNotFoundException;
import com.example.petshop.Models.UserModel;
import com.example.petshop.Repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    @Autowired
    private final UserRepository userRepository;

    @Autowired
    private final JwtService jwtService;

    public UserDto getUserInfo(String authorizationHeader) {
        var email = jwtService.extractUsername(authorizationHeader.substring(7));
        var user = userRepository.findByEmail(email);
        if (user.isPresent()) {
            UserDto dto = new UserDto();
            dto.setId(user.get().getId());
            dto.setFirstname(user.get().getFirstname());
            dto.setLastname(user.get().getLastname());
            dto.setEmail(user.get().getEmail());
            dto.setRole(user.get().getRole());

            return dto;
        } else {
            throw new UserNotFoundException("User not found.");
        }
    }

    public AuthenticationResponse updateUser(UserModel userModel) {
        if (!checkIfUserExists(userModel.getId())) {
            throw new UserNotFoundException("User not found");
        }

        userRepository.save(userModel);
        var jwtToken = jwtService.generateToken(userModel);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }

    public boolean checkIfUserExists(Long id) {
        Optional<UserModel> user = userRepository.findById(id);
        return user.isPresent();
    }

}
