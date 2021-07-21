package io.muzoo.ssc.project.backend.controller;

import io.muzoo.ssc.project.backend.entity.RestaurantEntity;
import io.muzoo.ssc.project.backend.repository.RestaurantRepo;
import io.muzoo.ssc.project.backend.request.CreateRestaurantRequest;
import io.muzoo.ssc.project.backend.response.DeleteResponseDTO;
import io.muzoo.ssc.project.backend.response.RestaurantResponse;
import lombok.RequiredArgsConstructor;
import org.hibernate.sql.Delete;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
public class RestaurantController {

    private final RestaurantRepo restaurantRepo;

    @PostMapping("/api/restaurant")
    public RestaurantResponse createRestaurant(@ModelAttribute CreateRestaurantRequest request) throws IOException {
        RestaurantEntity restaurant = new RestaurantEntity();
        // save restaurant details into database
        restaurant.setName(request.getName());
        restaurant.setPhoto(request.getPhoto().getBytes());
        restaurant.setDescription(request.getDescription());
        restaurant.setAddress(request.getAddress());
        restaurantRepo.save(restaurant);

        // respond back to frontend
        RestaurantResponse restaurantResponse = new RestaurantResponse();
        restaurantResponse.setId(restaurant.getId());
        return restaurantResponse;
    }

    @DeleteMapping("/api/restaurant/{restaurantId}")
    public DeleteResponseDTO deleteRestaurant(@PathVariable("restaurantId") long restaurantId) {
        DeleteResponseDTO deleteResponseDTO = new DeleteResponseDTO();
        deleteResponseDTO.setId(restaurantId);
        restaurantRepo.deleteById(restaurantId);
        return deleteResponseDTO;
    }




}
