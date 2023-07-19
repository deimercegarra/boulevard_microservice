package com.pragma.boulevard_microservice.domain.api;

import com.pragma.boulevard_microservice.domain.model.CommonResponseModel;
import com.pragma.boulevard_microservice.domain.model.RestaurantModel;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IRestaurantServicePort {

    CommonResponseModel saveRestaurant(RestaurantModel restaurantModel);

    List<RestaurantModel> getAllRestaurants(Pageable pageable);

    RestaurantModel getRestaurant(Long restaurantId);

    void updateRestaurant(RestaurantModel restaurantModel);

    void deleteRestaurant(Long restaurantId);

}