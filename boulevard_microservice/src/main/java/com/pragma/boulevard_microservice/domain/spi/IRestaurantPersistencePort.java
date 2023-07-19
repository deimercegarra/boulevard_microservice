package com.pragma.boulevard_microservice.domain.spi;

import com.pragma.boulevard_microservice.domain.model.RestaurantModel;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IRestaurantPersistencePort {

    RestaurantModel saveRestaurant(RestaurantModel restaurantModel);

    List<RestaurantModel> getAllRestaurants(Pageable pageable);

    RestaurantModel getRestaurant(Long restaurantId);

    RestaurantModel updateRestaurant(RestaurantModel restaurantModel);

    void deleteRestaurant(Long restaurantId);

}