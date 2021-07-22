package io.muzoo.ssc.project.backend.controller;

import io.muzoo.ssc.project.backend.entity.RestaurantEntity;
import io.muzoo.ssc.project.backend.repository.RestaurantRepo;
import io.muzoo.ssc.project.backend.request.CreateRestaurantRequest;
import io.muzoo.ssc.project.backend.response.DeleteResponseDTO;
import io.muzoo.ssc.project.backend.response.RestaurantResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

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

    @GetMapping("/api/restaurant/list")
    public List<RestaurantResponse> getTop10RestaurantList() {

        List<RestaurantEntity> restaurantEntities = restaurantRepo.findTop10ByOrderByIdAsc();
        List<RestaurantResponse> restaurantResponses = new ArrayList<>();
        for (int i = 0; i < restaurantEntities.size(); i++) {
            RestaurantResponse restaurantResponse = new RestaurantResponse();
            restaurantResponse.setId(restaurantEntities.get(i).getId());
            restaurantResponse.setName(restaurantEntities.get(i).getName());
            byte[] encodePhoto = Base64.getEncoder().encode(restaurantEntities.get(i).getPhoto());
            restaurantResponse.setPhoto(encodePhoto);
            restaurantResponse.setAddress(restaurantEntities.get(i).getAddress());

            restaurantResponses.add(restaurantResponse);
        }
        // respond back to frontend

        return restaurantResponses;
    }




}
