package io.muzoo.ssc.project.backend.controller;

import io.muzoo.ssc.project.backend.entity.RestaurantEntity;
import io.muzoo.ssc.project.backend.repository.RestaurantRepo;
import io.muzoo.ssc.project.backend.request.CreateRatingRequest;
import io.muzoo.ssc.project.backend.request.CreateUserRequest;
import io.muzoo.ssc.project.backend.entity.UserEntity;
import io.muzoo.ssc.project.backend.repository.UserRepo;
import io.muzoo.ssc.project.backend.response.DeleteResponseDTO;
import io.muzoo.ssc.project.backend.response.RatingResponse;
import io.muzoo.ssc.project.backend.response.UserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor

public class CreateUserController {

    private final UserRepo userRepo;
    private final PasswordEncoder passwordEncoder;

    @PostMapping("/api/user")
    public UserResponse createUser(@RequestBody CreateUserRequest request) {
        UserEntity user = new UserEntity();
        // set+save user into database
        user.setUserName(request.getUsername());
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setDateOfbirth(request.getDateOfBirth());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        userRepo.save(user);

        // respond back to frontend
        UserResponse userResponse = new UserResponse();
        userResponse.setUsername(user.getUserName());
        userResponse.setId(user.getId());
        return userResponse;
    }

    @GetMapping("/api/user/{userid}")
    public UserResponse getUser(@PathVariable("userid") long userid) {

        UserEntity user = userRepo.findById(userid).get();

        UserResponse userResponse = new UserResponse();
        userResponse.setUsername(user.getUserName());
        userResponse.setId(user.getId());
        return userResponse;
    }

    @GetMapping("/api/user/list")
    public List<UserResponse> getUserList() {

        List<UserEntity> userEntities = userRepo.findAll();
        List<UserResponse> userResponses = new ArrayList<>();
        for (int i = 0; i < userEntities.size(); i++) {
            UserResponse userResponse = new UserResponse();
            userResponse.setUsername(userEntities.get(i).getUserName());
            userResponse.setId(userEntities.get(i).getId());
            userResponses.add(userResponse);
        }

        // respond back to frontend
        return userResponses;
    }

    @DeleteMapping("/api/user/{userid}")
    public DeleteResponseDTO deleteUser(@PathVariable("userid") long userid) {
        DeleteResponseDTO deleteUserResponse = new DeleteResponseDTO();
        deleteUserResponse.setId(userid);
        userRepo.deleteById(userid);
        return deleteUserResponse;
    }

}
