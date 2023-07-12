package com.pragma.boulevard_microservice.domain.spi;

import com.pragma.boulevard_microservice.domain.model.RestaurantModel;

import java.util.List;

public interface IRestaurantPersistencePort {

    void saveRestaurant(RestaurantModel restaurantModel);

    List<RestaurantModel> getAllRestaurants();

    RestaurantModel getRestaurant(Long restaurantId);

    void updateRestaurant(RestaurantModel restaurantModel);

    void deleteRestaurant(Long restaurantId);

}